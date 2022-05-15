package Contas;

import Contas.CLC_Imposto;

import java.util.Random;

public class RendaFixa extends Conta implements CLC_Imposto {


    private static final double IMPOSTO = 0.15;
    private static final double RENDIMENTO_MIN = 0.005;
    private static final double RENDIMENTO_MAX = 0.0085;
    private static final double DESC_FIDELIDADE = 0.5;
    private static double impostoRedimento;
    private static double remuneraçãoRendimento;
    private int totalPTS;


    /**
     * Construtor da classe Investimento, recebendo o contrustor da
     * classe mãe ja com descrição da classe é iniciando a pontuação com zero
     * pois a conta e nova.
     */
    public RendaFixa() {
        super("Renda Fixa");
        this.totalPTS = 0;
    }


    public int getTotalPTS() {
        return totalPTS;
    }


    public void setTotalPTS(int totalPTS) {
        this.totalPTS = totalPTS;
    }

    /**
     * Metodo de saque.
     * @param valorSaque
     * @return vai retorna o saldo em conta.
     */
    public double saque(double valorSaque) {
        saldo = this.saldo - (valorSaque + calcImposto(valorSaque));
        return saldo;
    }


    /**
     * Metodo para saber o valor taxa sobre o valor de saque
     * ja com as regras de fidelidade PTS é ja com a cobrança de imposto sobre o saque.
     * @param valorSaque
     * @return vai retornar a taxa que sera cobrada no saque.
     */
    @Override
    public double calcImposto(double valorSaque) {
        double taxaSaque;
        double aux;
        if (totalPTS >= 1200) {
            this.totalPTS -= 1200;
            aux = (IMPOSTO * DESC_FIDELIDADE);
            taxaSaque = valorSaque * aux;
        } else {
            taxaSaque = valorSaque * IMPOSTO;
        }
        System.out.println("Valor taxa sobre saque: " + taxaSaque);
        return taxaSaque;
    }


    /**
     * Metodo para gerar o um numero aleatorio entre o Minimo e o Maximo de redimento que a conta pode ter.
     * @return vai returno o percentual de redimento que cliente vai ter no mês.
     */
    private double rendimentoMes() {
        Random random = new Random();
        impostoRedimento = random.nextDouble(RENDIMENTO_MAX - RENDIMENTO_MIN) + RENDIMENTO_MIN;
        return impostoRedimento;
    }

    /**
     * Metodo para calcular a remuneração de rendimento que o cliente
     * vai ter no mês.
     * @return vai retorna o valor da remuneração do rendimento aplicado.
     */
    @Override
    public double calRendimento() {
        saldo += this.saldo * impostoRedimento;
        remuneraçãoRendimento = saldo * impostoRedimento;
        return remuneraçãoRendimento;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Total Pontos: " + totalPTS + "\n" +
                "Variação de Rendimento Mensal: " + String.format("%.4f%n", impostoRedimento) +
                "Remuneração Rendimento Mensal: " + String.format("%.4f%n", remuneraçãoRendimento);
    }

}
