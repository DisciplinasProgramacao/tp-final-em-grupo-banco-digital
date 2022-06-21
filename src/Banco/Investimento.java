package Banco;

import Cliente.Cliente;

import java.io.Serializable;
import java.util.Random;

public class Investimento extends Conta implements CALC_Imposto, Serializable {


    private static final double IMPOSTO = 0.225;
    private static final double RENDIMENTO_MIN = -0.002;
    private static final double RENDIMENTO_MAX = 0.015;
    private static final double DESC_FIDELIDADE = 0.5;
    private static final double BOUNS_REND = 0.1;
    private static int totalPTS;


    public Investimento() {
        super("INVESTIMENTO");
        this.totalPTS = 0;
    }



    @Override
    public double saque(double valor) {
            this.saldo = this.saldo - (valor + calcImposto(valor));;
            listOpecao.add(new Opercao("saque", (valor + calcImposto(valor))));
            return this.saldo;
    }

    @Override
    public double calcImposto(double valorSaque) {
        if (temDescontoImposto() == true) {
            return (valorSaque * IMPOSTO) * DESC_FIDELIDADE;
        } else {
            return valorSaque * IMPOSTO;
        }
    }


    private double taxaRendimentoMes() {
        Random random = new Random();
        return random.nextDouble(RENDIMENTO_MAX - RENDIMENTO_MIN) + RENDIMENTO_MIN;
    }


    @Override
    public double calRendimento() {
        if(this.saldo > 0){
            if (temBonusRend() == true) {
                return this.saldo + ((saldo * taxaRendimentoMes()) * BOUNS_REND);
            } else {
                return this.saldo + (saldo * taxaRendimentoMes());
            }
        }else{
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
        if (totalPTS >= 2000) {
            totalPTS -= 2000;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return super.toString() + "\n" +
                "PONTOS: " + totalPTS + "\n" +
                "TAXA DE RENDIMENTO MENSAL: " + String.format("%.4f%n", taxaRendimentoMes()) +
                "REMUNERAÇÃO RENDIMENTO MENSAL: " + String.format("%.4f%n", calRendimento());

    }


}
