package Banco;
public class Conta {

    private double saldo;
    private String tipo;

    public Conta(String tipo) {
        this.setSaldo(0.0);
        this.tipo = tipo;
    }

    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double saque(double valor) {
        return getSaldo() - valor;
    }

    public double deposito(double valor) {
        return getSaldo() + valor;
    }

    @Override
    public String toString() {
        return "Saldo: " + getSaldo() +
                "Tipo Conta" + tipo;
    }

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}

