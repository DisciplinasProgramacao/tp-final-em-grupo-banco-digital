package Cliente;

import Banco.Conta;
import Banco.RendaFixa;

public class ClienteGold extends Cliente {

	private static final double TAXA_MENSAL = 10;
	private static final int PONTOS_MES = 10;
	private static final double SAQUE_ESPECIAL = 300;

	private int totalPonto;
	private double ConsumoMensal;

	RendaFixa conta = new RendaFixa(); // Remover quanto as constas tiverem sido implementadas

	public ClienteGold(String nome, String cpf, String senha, Conta Conta) {
		super(nome, cpf, senha, Conta);		
		calPts(); // Passar as contas por parametros? obs, talvez criar um metodo que adiciona uma conta ha um cliente

	}
	
	public String toString() {
		StringBuilder linha = new StringBuilder();
		linha.append("--- Dados do Cliente ----\n");
		linha.append("Nome: " + nome + "\n");
		linha.append("CPF: " + cpf + "\n");
		linha.append("Categoria: Gold\n");
		linha.append("Pontos de fidelidade: " + this.totalPonto + "\n");
		linha.append("Contas do cliente: \n");
//		listaConta.forEach(conta -> linha.append(conta + "\n"));
		return linha.toString();
	}

	@Override
	public double calTarifa() {
		return this.TAXA_MENSAL;
	}

//	@Override
//	public double calBonus() {
//		conta.setSaldo(conta.getSaldo() - SAQUE_ESPECIAL); 
//		return conta.getSaldo();
//	}

//	@Override
//	public int calPts() {
//		this.totalPonto += (this.conta.getSaldo() / 1000 ) + PONTOS_MES;
//		return 0;
//	}

	@Override
	public int verificarBeneficios() {
		return (int) SAQUE_ESPECIAL;
	}


}
