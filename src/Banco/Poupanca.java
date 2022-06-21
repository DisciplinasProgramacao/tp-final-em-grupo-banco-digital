package Banco;

import java.io.Serializable;

public class Poupanca extends Conta implements CALC_Rendimento, Serializable {

    private static final double REDIMENTO = 0.006;


    public  Poupanca() {
        super("POUPANÇA");
    }


    @Override
    public double calRendimento() {
        if(this.saldo > 0){
            return saldo + (this.saldo * REDIMENTO);
        }else{
            return 0;
        }
    }


    public String toString() {
        return "\n" + super.toString() +
                "RENDIMENTO DO MÊS: " + String.format("%.2f",calRendimento()) + "\n";
    }


}
