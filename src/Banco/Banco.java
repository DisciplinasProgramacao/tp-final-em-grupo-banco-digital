package Banco;

import Aplicacao.Validation;
import Cliente.Cliente;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Banco implements Serializable {

    private Set<Cliente> listaCliente;



    public Banco(Cliente cliente) throws FileNotFoundException {
        this.listaCliente = new HashSet<>();
        addCliente(cliente);
        salvarDados();
    }


    public Banco() throws FileNotFoundException {
        this.listaCliente = lerBaseDados();

    }


    public void addCliente(Cliente cliente) {
        this.listaCliente.add(cliente);
        salvarDados();
    }


    public String percorrerList() {
        StringBuilder sb = new StringBuilder("Lista de Clientes \n");
        for (Cliente cliente : listaCliente) {
            sb.append(cliente);
            sb.append("===================================");
        }
        return sb.toString();
    }

    public Cliente buscarCliente(String cpf) {
        return listaCliente.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findAny()
                .orElse(null);

    }


    public double calcCustodia(String tipo) {
        return  listaCliente.stream()
                .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(cliente -> cliente.percorrerContas().getSaldo())
                .sum();
    }


    public double calcMedia(String tipo) {
        return  listaCliente.stream()
                .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(cliente -> cliente.percorrerContas().getSaldo())
                .average()
                .orElse(0.0);
    }

    public Cliente calcExtremoMAx(String tipo) {
       return  listaCliente.stream()
                .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                .collect(Collectors.toList())
                .stream()
                .max(Comparator.comparing(value -> value.percorrerContas().getSaldo()))
                .get();
    }


    public Cliente calcExtremoMin(String tipo) {
        return listaCliente.stream()
                .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                .collect(Collectors.toList())
                .stream()
                .min(Comparator.comparing(cliente -> cliente.percorrerContas().getSaldo()))
                .get();

    }


    public Set<Cliente> lerBaseDados() {
        HashSet<Cliente> listDados = new HashSet<>();
        try (FileInputStream dados = new FileInputStream("baseDados.bin")) {
            ObjectInputStream obj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Cliente cliente = (Cliente) obj.readObject();
                listDados.add(cliente);
            }
            obj.close();
        } catch (IOException e) {
            System.out.println("BASE DA DADOS VAZIA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listDados;
    }


    public void salvarDados() {
        try (ObjectOutputStream bjout = new ObjectOutputStream(new FileOutputStream("baseDados.bin"))) {
            for (Cliente cliente : listaCliente) {
                bjout.writeObject(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int hashCode() {
        return this.percorrerList().charAt(0);
    }


    public boolean eguals(Cliente clinte, String tipo) throws Validation {
        Conta conta = clinte.percorrerContas();
        try {
            if (conta.getDescricao().equals(tipo)) {
                throw new Validation("CLIENTE JÁ POSSUI ESSE TIPO DE CONTA");
            } else {
                return false;
            }
        }catch (NullPointerException nex){
            return false;
        }
    }

}
