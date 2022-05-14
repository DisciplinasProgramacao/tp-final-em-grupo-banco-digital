package Cliente;

import Banco.Conta;
import Banco.RendaFixa;

public class ClienteVip extends Cliente{
	
	private static final double TAXA_MENSAL = 30;
	private static final int PONTOS_MES = 35;
	private static final int PONTOS_GASTO = 35;
	private static final double SAQUE_ESPECIAL = 1000;
	
	private int totalPonto;
	private double ConsumoMensal;
	
	RendaFixa conta = new RendaFixa(); // Remover quanto as constas tiverem sido implementadas

	public ClienteVip(String nome, String cpf, String senha) {
		super(nome, cpf, senha);
		calPts();
	}
	
	public String toString() {
		StringBuilder linha = new StringBuilder();
		linha.append("--- Dados do Cliente ----\n");
		linha.append("Nome: " + nome + "\n");
		linha.append("CPF: " + cpf + "\n");
		linha.append("Categoria: Vip\n");
		linha.append("Pontos de fidelidade: " + this.totalPonto + "\n");
		linha.append("Contas do cliente: \n");
		listaConta.forEach(conta -> linha.append(conta + "\n"));
		return linha.toString();
	}
	
	@Override
	public double calTarifa() {
		return this.TAXA_MENSAL;
	}
	
	@Override
	public double calBonus() {
		return 0;
	}
	
	@Override
	public int calPts() {
		this.totalPonto += (this.conta.getSaldo() / 1000 ) + PONTOS_MES;
		return 0;
	}
	
	@Override
	public int verificarBeneficios() {
		return (int) SAQUE_ESPECIAL;
	}

	
	
}
