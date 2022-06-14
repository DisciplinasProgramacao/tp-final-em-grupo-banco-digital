package Banco;

import Cliente.Cliente;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Banco implements Serializable {

    private Set<Cliente> listaCliente;
    private double saldoTotal;
    private double saldoMedio;



    public Banco(Cliente cliente) throws FileNotFoundException {
        this.listaCliente = new HashSet<>();
        addCliente(cliente);
        salvarDados();
    }


    public Banco() throws FileNotFoundException {
        this.listaCliente = lerBaseDados();

    }


    public void addCliente(Cliente cliente) throws FileNotFoundException {
        this.listaCliente.add(cliente);
        salvarDados();
    }

    public String percorrerList(){
        StringBuilder sb = new StringBuilder("Lista de Clientes \n");
        for (Cliente cliente : listaCliente){
            sb.append(cliente);
            sb.append("===================================");
        }
        return sb.toString();
    }

    public Cliente buscarCliente(String cpf){
        return  listaCliente.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findAny()
                .orElse(null);

    }


    public double calcCustodia(String tipo) {
        this.saldoTotal = listaCliente.stream()
                .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(cliente -> cliente.percorrerContas().getSaldo())
                .sum();
        return this.saldoTotal;
    }


    public String calcMedia(String tipo) {
        this.saldoMedio = listaCliente.stream()
                .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(cliente -> cliente.percorrerContas().getSaldo())
                .average()
                .orElse(0.0);

        return  String.format("MEDIA TOTAL: " + this.saldoMedio);
    }

    public String calcExtremoMAx(String tipo) {
          double aux = listaCliente.stream()
                                 .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                                 .collect(Collectors.toList())
                                 .stream()
                                 .mapToDouble(value -> value.percorrerContas().getSaldo())
                                 .max()
                                 .orElseThrow(IllegalStateException::new);
        return String.format("VALOR EXTREMO MAXIMO: "+ aux);
    }


    public  String calcExtremoMin(String tipo) {
        double aux = listaCliente.stream()
                            .filter(cliente -> cliente.percorrerContas().getDescricao().equals(tipo))
                            .collect(Collectors.toList())
                            .stream()
                            .mapToDouble(cliente -> cliente.percorrerContas().getSaldo())
                            .min()
                            .orElseThrow(IllegalStateException::new);
        return String.format("VALOR EXTREMO MINIMO: "+ aux);
    }


    public Set<Cliente> lerBaseDados() throws FileNotFoundException {
        HashSet<Cliente> listDados = new HashSet<>();
        try (FileInputStream  dados = new FileInputStream("baseDados.bin")) {
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


    public void salvarDados() throws FileNotFoundException {
        try(ObjectOutputStream bjout = new ObjectOutputStream(new FileOutputStream("baseDados.bin"))) {
            for(Cliente cliente : listaCliente){
                bjout.writeObject(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean validarConta(Cliente clinte, String tipo){
        Conta conta = clinte.percorrerContas();
        if(conta.getDescricao() == tipo){
            return  true;
        }else{
            return false;
        }
    }

}
