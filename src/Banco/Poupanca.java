package Banco;

import java.io.Serializable;

public class Poupanca extends Conta implements CALC_Rendimento, Serializable {

    private static final double REDIMENTO = 0.006;


    public  Poupanca() {
        super("POUPANÇA");
    }


    /**
     * Metodo para calcular a remuneração de rendimento que o cliente
     * vai ter no mês.
     * @return vai retorna o valor da remuneração do rendimento aplicado.
     */
    @Override
    public double calRendimento() {
        return saldo + (this.saldo * REDIMENTO);
    }


    public String toString() {
        return "\n" + super.toString() +
                "RENDIMENTO DO MÊS: " + String.format("%.2f",calRendimento()) + "\n";
    }


}
