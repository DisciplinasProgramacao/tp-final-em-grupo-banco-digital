package Banco;
public class Conta {

    private double saldo;
    private String tipo;
    private String id_conta;
    static Integer aux = 0;

    public Conta(String tipo) {    	
    	id_conta = String.valueOf(aux);
    	aux++;
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
        return "Numero da conta: " + this.id_conta +
                "\nTipo Conta: " + this.tipo;
    }

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getId_conta() {
		return id_conta;
	}
	
	public static void main(String[] args) {
		Conta conta = new Conta("aaaa");
		System.out.println(conta);
	}
}

