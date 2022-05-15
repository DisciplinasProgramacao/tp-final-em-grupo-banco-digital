package Contas;

public class Poupança extends Conta implements CLC_Rendimento {

    private static final double REDIMENTO = 0.006;


    public Poupança() {
        super("Contas.Poupança");
    }



    /**
     * Metodo para calcular a remuneração de rendimento que o cliente
     * vai ter no mês.
     * @return vai retorna o valor da remuneração do rendimento aplicado.
     */
    @Override
    public double calRendimento() {
        saldo += this.saldo * REDIMENTO;
        return saldo * REDIMENTO;
    }


    public String toString() {
        return super.toString() + "\n" +
                "Remuneração Rendimento:" + String.format("%.2f",calRendimento());
    }


}
