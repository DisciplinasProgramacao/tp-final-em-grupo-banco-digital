package Cliente;

import java.util.LinkedList;
import java.util.List;

import Banco.Conta;

public class Cliente {

	private static final double TAXA_MENSAL = 0;
	
	protected String nome;
	protected String cpf;
	protected String senha;
	protected List<Conta> listaConta;
	
	public Cliente(String nome, String cpf, String senha) {
			this.nome = nome;
			this.cpf = cpf;
			this.senha = senha;
			listaConta = new LinkedList<>();
	}

	@Override
	public String toString() {
		StringBuilder linha = new StringBuilder();
		linha.append("--- Dados do Cliente ----\n");
		linha.append("Nome: " + nome + "\n");
		linha.append("CPF: " + cpf + "\n");
		linha.append("Categoria: Regular\n");
		linha.append("Contas do cliente: \n");
		listaConta.forEach(conta -> linha.append(conta + "\n"));
		return linha.toString();
	}

	/**
	 * Calcula os pontos de fidelidade que o cliente possui.
	 * @return Pontos de fidelidade.
	 */
	public int calPts() {
		return 0;
	}
	
	/**
	 *Verifica se o cliente tem algum benefício.
	 * @return Valor dos beneficios que o cliente tem direito
	 */
	public boolean verificarBeneficios() {
		return false;
	}
	
	/**
	 * Tarifa paga por conta
	 * @return saldo - tarifa.
	 */
	public void calTarifa(Conta conta) {		
		conta.setSaldo(conta.getSaldo() - TAXA_MENSAL); 
	}	
	
	
	//------------------------------------------------------------------------------------------
	/**
	 * Vincula uma conta a um cliente
	 * @param conta
	 * @return Void
	 */
	public void vincularConta(Conta conta) {
		if(verificaContasExistentes(conta) == false) {
			this.listaConta.add(conta);
		}else {
			System.out.println("Nao foi possivel adicionar conta");
		}
		
		calPts(); 
	}
	
	/**
	 * Verifica se o cliente possui mais de uma conta do memso tipo
	 * @param conta
	 * @return boolean
	 */
	private boolean verificaContasExistentes(Conta conta) {
		return false;
	}
	
	

}
