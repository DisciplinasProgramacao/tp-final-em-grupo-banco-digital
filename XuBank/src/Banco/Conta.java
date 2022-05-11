package Banco;
public class Conta {

    protected double saldo;
    private String tipo;

    public Conta(String tipo) {
        this.saldo = 0.0;
        this.tipo = tipo;
    }

    public double saque(double valor) {
        return saldo - valor;
    }

    public double deposito(double valor) {
        return saldo + valor;
    }

    public String toString() {
        return "Saldo: " + saldo +
                "Tipo Conta" + tipo;
    }
}
