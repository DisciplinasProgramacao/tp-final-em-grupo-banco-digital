package Banco;

import Cliente.Cliente;

import java.io.Serializable;
import java.util.Random;

public class RendaFixa extends Conta implements CALC_Imposto, Serializable {

    private static final double IMPOSTO = 0.15;
    private static final double RENDIMENTO_MIN = 0.005;
    private static final double RENDIMENTO_MAX = 0.0085;
    private static final double DESC_FIDELIDADE = 0.5;
    private  int totalPTS;


    public RendaFixa() {
        super("RENDA FIXA");
        this.totalPTS = 0;
    }



    @Override
    public double saque(Cliente cliente, double valor) {
         calRendimento();
        if(valor > this.saldo){
            if(cliente.getCategoria().temSaqueEspecial() == 0){
                System.out.println("Saldo insuficiente");
                System.out.println("SALDO: " + this.saldo);
                return this.saldo;
            } else{
                this.saldo = limiteCheque(cliente,valor);
                return this.saldo;
            }
        }else{
            this.saldo = this.saldo - (valor + calcImposto());
            listOpecao.add(new Opercao(EnumOperacao.SAQUE, (valor + calcImposto())));
            return this.saldo;
        }
    }


    public double limiteCheque(Cliente cliente, double valor){
        double limite = -valor + (this.saldo);
        if(-cliente.getCategoria().temSaqueEspecial() <= limite ){
            this.saldo = this.saldo - (valor + calcImposto());;
            listOpecao.add(new Opercao(EnumOperacao.SAQUE, (valor + calcImposto())));
            return this.saldo;
        }else{
            System.out.println("Limite de cheque especial excedido");
            return this.saldo;
        }
    }



    @Override
    public double calcImposto() {
        if(this.saldo > 0){
            if (temDescontoImposto() == true) {
                return (calRendimento() * IMPOSTO) * DESC_FIDELIDADE;
            } else {
                return calRendimento() * IMPOSTO;
            }
        }else{
            return 0;
        }

    }



    private double taxaRendimentoMes() {
        Random random = new Random();
        return random.nextDouble(RENDIMENTO_MAX - RENDIMENTO_MIN) + RENDIMENTO_MIN;
    }



    @Override
    public double calRendimento() {
        if(this.saldo > 0){
            return this.saldo += (saldo * taxaRendimentoMes());
        }else {
            return 0;
        }

    }


    @Override
    public boolean temDescontoImposto() {
        if (totalPTS >= 1200) {
            this.totalPTS -= 1200;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean temBonusRend() {
        return false;
    }



    public String toString() {
        return super.toString() +
                "PONTOS: " + totalPTS + "\n" +
                "TAXA DE RENDIMENTO MENSAL: " + String.format("%.4f%n", taxaRendimentoMes()) +
                "REMUNERA????O RENDIMENTO MENSAL: " + String.format("%.4f%n", calRendimento());

    }

}
