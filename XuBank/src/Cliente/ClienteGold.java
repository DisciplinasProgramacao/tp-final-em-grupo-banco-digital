package Cliente;

import Banco.Conta;
import Banco.RendaFixa;

public class ClienteGold extends Cliente {

	private static final double TAXA_MENSAL = 10;
	private static final int PONTOS_MES = 10;
	private static final double SAQUE_ESPECIAL = 300;

	private int totalPonto;
	private double ConsumoMensal; //Que isso msm kk?
	

	public ClienteGold(String nome, String cpf, String senha) {
		super(nome, cpf, senha);		
		calPts();
	}
	
	public String toString() {
		StringBuilder linha = new StringBuilder();
		linha.append("--- Dados do Cliente ----\n");
		linha.append("Nome: " + nome + "\n");
		linha.append("CPF: " + cpf + "\n");
		linha.append("Categoria: Gold\n");
		linha.append("Pontos de fidelidade: " + this.totalPonto + "\n");
		linha.append("Contas do cliente: \n");
		listaConta.forEach(conta -> linha.append("  "+conta + "\n"));
		linha.append("Beneficios disponiveis do cliente: "+ this.verificarBeneficios()+"\n");
		return linha.toString();
	}

	@Override
	public int calPts() {
		double somaValores =0;
		for(int i =0; i< super.listaConta.size(); i++) {			
			somaValores += super.listaConta.get(i).getSaldo();
		}
		this.totalPonto = (int) (( somaValores/ 1000 ) + PONTOS_MES);
		return this.totalPonto;
	}

	@Override
	public boolean verificarBeneficios() {
		return true;
	}


}
