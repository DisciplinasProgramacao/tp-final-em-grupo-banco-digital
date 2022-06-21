package Cliente;

import Aplicacao.Validation;
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
      for(Conta conta: listContas){
          return conta;
      }
      return null;
    }


    public double valorTotal() {
        return listContas.stream()
                .mapToDouble(conta -> conta.getSaldo())
                .sum();
    }


    public boolean verificarCheque(){
        if (this.categoria.temSaqueEspecial() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean limiteDisponivel(int nunconta) throws Validation {
        if(buscarConta(nunconta).getSaldo() == categoria.temSaqueEspecial()){
            throw new Validation("LIMITE DE CHEQUE ESPECIAL EXCEDIDO");
        }else{
            return false;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nNOME: " + this.nome + "\n");
        sb.append("CPF: " + this.cpf + "\n");
        sb.append(categoria);
        this.listContas.forEach(conta -> sb.append(conta + "\n"));
        return sb.toString();
    }

}
