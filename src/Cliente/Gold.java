package Cliente;



import java.io.Serializable;


public class Gold implements IEspecial, Serializable {

    private static double TAXA_MENSAL = 10;
    private static int BONUS = 10;
    private static int PONTOS_MES = 10;
    private static int BONUS_SALDO = 10;
    private static int LIMITE_ESPECIAL = 300;
    private  String tipoCliente;
    private int totalPontos;


    public Gold() {
        this.totalPontos = 0;
        this.tipoCliente = "GOLD";
    }

    public int getTotalPontos() {
        return totalPontos;
    }

    @Override
    public  int temSaqueEspecial() {
        return LIMITE_ESPECIAL;
    }


    @Override
    public int calcBonusPontos(double valorTotal) {
        if (valorTotal >= 1000) {
           return this.totalPontos = ((int) valorTotal / 1000) * BONUS_SALDO;
        }else{
            return this.totalPontos;
        }
    }

    public String toString(){
        return "CLIENTE: " + this.tipoCliente + "\n" +
                "TOTAL PONTOS: " + this.totalPontos + "\n";
    }


}
