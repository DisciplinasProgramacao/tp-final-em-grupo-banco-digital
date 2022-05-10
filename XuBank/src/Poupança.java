public class Poupança extends Conta implements CLC_Rendimento {

    private static final double REDIMENTO = 0.006;
    private boolean temRedimento;


    public Poupança() {
        super("Poupança");
        this.temRedimento = true;
    }


    @Override
    public double calRendimento() {
        return super.saldo * REDIMENTO;
    }
}
