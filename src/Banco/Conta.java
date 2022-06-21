package Banco;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Conta implements Serializable {

    protected double saldo;
    protected int numConta;
    private String descricao;
    protected LinkedList<Opercao> listOpecao;
    private Random random = new Random();


    public Conta() {
        this.saldo = 0;
        this.numConta = random.nextInt(100);
        this.saldo = 0.0;
        this.listOpecao = new LinkedList<>();
        this.descricao = "CORRENTE";
    }


    public Conta(String descricao) {
        this.saldo = 0;
        this.numConta = random.nextInt(100);
        this.saldo = 0.0;
        this.listOpecao = new LinkedList<>();
        this.descricao = descricao;
    }


    public  double getSaldo() {
        return saldo;
    }

    public int getNumConta() {
        return numConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public double saque(double valor) {
            this.saldo = this.saldo - valor;
            listOpecao.add(new Opercao("saque", valor));
            return this.saldo;
    }


    public double deposito(double valor) {
        this.saldo = this.saldo + valor;
        listOpecao.add(new Opercao("deposito", valor));
        return this.saldo;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("CONTA N°: " + this.numConta + "  ");
        sb.append("TIPO: " + descricao + "  ");
        sb.append("SALDO: " + String.format("%.2f", saldo) + "\n");
        sb.append("EXTRATO: \n");
        for (Opercao opercao : listOpecao) {
            sb.append(opercao);
        }
        return sb.toString();
    }


}

