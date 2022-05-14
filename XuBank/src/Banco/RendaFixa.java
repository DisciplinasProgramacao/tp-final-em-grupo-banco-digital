package Banco;
public class RendaFixa extends Conta implements CLC_Imposto {


    private static final double IMPOSTO = 0.15;
    private static final double RENDIMENTO_MIN = 0.005;
    private static final double RENDIMENTO_MAX = 0.0085;
    private static final double DESC_FIDELIDADE = 0.5;
    private static final int PTS_BONUS = 1200;


    public RendaFixa() {
        super("Renda Fixa");

    }


    public static double descSaque(){
        return (IMPOSTO * DESC_FIDELIDADE) * 100;
    }
    
    @Override
    public double saque(double valorsaque) {
        return getSaldo() - (valorsaque + calcImposto(valorsaque));
    }

    @Override
    public double calcImposto(double valorSaque) {
        return (valorSaque * descSaque()) / 100;
    }

    @Override
    public double calRendimento() {
        return 0;
    }
}
