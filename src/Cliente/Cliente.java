package Cliente;

import Banco.Conta;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Cliente implements Serializable {

    private String nome;
    private String cpf;
    private String senha;
    private List<Conta> listContas;
    private IEspecial categoria;


    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.categoria = new Regular();
        this.listContas = new LinkedList<>();
    }


    public Cliente(String nome, String cpf, String senha, IEspecial categoria) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.categoria = categoria;
        this.listContas = new LinkedList<>();
    }

    public IEspecial getCategoria() {
        return categoria;
    }

    public void addConta(Conta conta) {
        this.listContas.add(conta);
    }



    public Conta buscarConta(int numConta) {
        return listContas.stream()
                .filter(conta -> conta.getNumConta() == numConta)
                .findAny()
                .orElse(null);
    }


    public String getCpf() {
        return cpf;
    }


    public Conta percorrerContas() {
        Conta conta = null;
        for (Conta aux : listContas) {
            conta = aux;
        }
        return conta;
    }


    public double valorTotal() {
        return listContas.stream()
                .mapToDouble(conta -> conta.getSaldo())
                .sum();
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nNOME: " + this.nome + "\n");
        sb.append("CPF: " + this.cpf + "\n");
        sb.append(categoria + "\n");
        this.listContas.forEach(conta -> sb.append(conta + "\n"));
        return sb.toString();
    }

}
