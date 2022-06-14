package Banco;

import java.io.Serializable;
import java.util.Random;

public class RendaFixa extends Conta implements CALC_Imposto, Serializable {

    private static final double IMPOSTO = 0.15;
    private static final double RENDIMENTO_MIN = -0.005;
    private static final double RENDIMENTO_MAX = 0.85;
    private static final double DESC_FIDELIDADE = 0.5;
    private static int totalPTS;


    public RendaFixa() {
        super("RENDA FIXA");
        this.totalPTS = 0;
    }


    public static int getTotalPTS() {
        return totalPTS;
    }


    public static void setTotalPTS(int totalPTS)  {
        RendaFixa.totalPTS = totalPTS;
    }


    @Override
    public double saque(double valorSaque) {
        this.saldo = this.saldo - (valorSaque + calcImposto(valorSaque));
        listOpecao.add(new Opercao("saque", (valorSaque + calcImposto(valorSaque))));
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
        return this.saldo + (saldo * taxaRendimentoMes());
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
                "REMUNERAÇÃO RENDIMENTO MENSAL: " + String.format("%.4f%n", calRendimento());

    }

}
