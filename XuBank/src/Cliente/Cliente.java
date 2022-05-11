package Cliente;

import java.util.List;
import Banco.Conta;

public class Cliente {

	protected String nome;
	protected String cpf;
	protected String senha;
	protected List<Conta> listaConta;

	public Cliente(String nome, String cpf, String senha, Conta conta) {
		if (!(nome.equals(null) || (nome.replaceAll("\\s+", "") == ""))) {
			this.nome = nome;
		} else {
			this.nome = "N/A";
		}

		if (!(senha.equals(null) || (senha.replaceAll("\\s+", "") == ""))) {
			this.cpf = cpf;
		} else {
			this.cpf = "N/A";
		}

		if (!(senha.equals(null) || (senha.replaceAll("\\s+", "") == ""))) {
			this.senha = senha;
		} else {
			this.senha = "0000";
		}

		if ((conta == null) || conta.equals(null)) {
			this.listaConta.add(conta);// Imaginando que o cliente pra ser cliente precise de uma conta de inicio
		}
	}

	@Override
	public String toString() {
		StringBuilder linha = new StringBuilder();
		linha.append("--- Dados do Cliente ----\n");
		linha.append("Nome: " + nome + "\n");
		linha.append("CPF: " + cpf + "\n");
		linha.append("Categoria: Regular\n");
		linha.append("Contas do cliente: \n");
//		listaConta.forEach(conta -> linha.append(conta + "\n"));
		return linha.toString();
	}

	/**
	 * Método para calcular tarifa do tipo de cliente.
	 * @return valor da tarifa do tipo de cliente.
	 */
	public double calTarifa() {
		return 0;
	}
	
	/**
	 * Método para calcular bônus com base no tipo de cliente.
	 * @return o valor do bônus em desconto em taxa. ????
	 */
	public double calBonus() { //Calcula bonus talvez nao tenha que ficar nas contas do banco?
		return 0;
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
	public int verificarBeneficios() { //Exibir os beneficios ou ve se tem beneficio disponivel??
		return 0;
	}

}
