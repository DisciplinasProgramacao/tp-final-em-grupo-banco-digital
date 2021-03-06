package Banco;

import Cliente.Cliente;

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


    public double getSaldo() {
        return saldo;
    }

    public int getNumConta() {
        return numConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public double saque(Cliente cliente, double valor) {
        if (valor > this.saldo) {
            if (cliente.getCategoria().temSaqueEspecial() == 0) {
                System.out.println("Saldo insuficiente");
                System.out.println("SALDO: " + this.saldo);
                return this.saldo;
            } else {
                this.saldo = this.saldo - valor;
                listOpecao.add(new Opercao(EnumOperacao.SAQUE, valor));
                return this.saldo;
            }
        } else {
            this.saldo = this.saldo - valor;
            listOpecao.add(new Opercao(EnumOperacao.SAQUE, valor));
            return this.saldo;
        }
    }


    public double deposito(double valor) {
        this.saldo = this.saldo + valor;
        listOpecao.add(new Opercao(EnumOperacao.DEPOSITO, valor));
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

