package Cliente;


import Banco.Conta;

import java.io.Serializable;
import java.util.List;

public class Vip implements IEspecial, Serializable {

    private static double TAXA_MENSAL = 30;
    private static int BONUS = 10;
    private static int PONTOS_MES = 35;
    private static int BONUS_SALDO = 30;
    private static double LIMITE_ESPECIAL = 1000;
    private String tipoCliente;
    private int totalPontos;


    public Vip() {
        this.totalPontos = 0;
        this.tipoCliente = "VIP";
    }


    @Override
    public boolean temSaqueEspecial() {
        return true;
    }


    @Override
    public int calcBonusPontos(double valorTotal) {
        if (valorTotal >= 2000) {
            return this.totalPontos = ((int) valorTotal / 2000) * BONUS_SALDO;
        }else{
            return this.totalPontos;
        }
    }


    public String toString() {
        return "CLIENTE: " + this.tipoCliente + "\n" +
                "TOTAL PONTOS: " + this.totalPontos + "\n";
    }


}
