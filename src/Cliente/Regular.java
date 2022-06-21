package Cliente;

import java.io.Serializable;

public class Regular implements IEspecial, Serializable {

    private static double TAXA_MENSAL = 0;
    private String tipoCliente;


    public Regular(){
        this.tipoCliente = "REGULAR";
    }


    @Override
    public int temSaqueEspecial() {
        return 0;
    }


    @Override
    public int calcBonusPontos(double valorTotal) {
        return 0;
    }


    public String toString() {
        return "CLIENTE: " + this.tipoCliente + "\n";
    }
}
