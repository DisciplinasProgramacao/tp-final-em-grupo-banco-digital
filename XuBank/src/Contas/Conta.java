package Contas;

public abstract class Conta {

    protected double saldo;
    private String tipo = "Contas.Conta corrente";

    public Conta(String tipo) {
        this.saldo = 0.0;
        this.tipo = tipo ;
    }


    public double  saque(double valor) {
        saldo = this.saldo - valor;
        return saldo;
    }


    public double deposito(double valor) {
        saldo = this.saldo + valor;
        return saldo;
    }


    public String toString() {
        return "Tipo Contas.Conta: " + tipo + "\n" +
                "Saldo: " + String.format("%.2f",saldo);
    }
}
