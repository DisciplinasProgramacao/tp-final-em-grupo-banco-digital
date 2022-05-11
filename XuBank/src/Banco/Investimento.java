package Banco;
public class Investimento extends Conta implements CLC_Imposto {


    private static final double IMPOSTO = 0.225;
    private static final double RENDIMENTO_MIN = -0.002;
    private static final double RENDIMENTO_MAX = 0.015;
    private static final double DESC_FIDELIDADE = 0.5;
    private static final double BOUNS_REND = 0.1;
    private static final int PTS_BONUS = 1200;


    public Investimento(String tipo) {
        super(tipo);
    }

    @Override
    public double calcImposto(double valorSaque) {
        return 0;
    }

    @Override
    public double calRendimento() {
        return 0;
    }
}
