package Banco;
public class Poupanca extends Conta implements CLC_Rendimento {

    private static final double REDIMENTO = 0.006;
    private boolean temRedimento;


    public Poupanca() {
        super("Poupanca");
        this.temRedimento = true;
    }


    @Override
    public double calRendimento() {
        return super.saldo * REDIMENTO;
    }
}
