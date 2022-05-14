package Cliente;

import Banco.*;

public class Aplicacao {

	private static final String NOME = "Andre";
	private static final String CPF = "12345678901";
	private static final String SENHA = "0000";
	private static final float UM_MIL = 1000;
	private static final float QUINHENTOS = 500;
	private static final float SETECENTOS = 700;
	private static final float TRES_MIL = 3000;

	public static void main(String[] args) {

		Conta conta = new RendaFixa();
		Conta conta1 = new Poupanca();
		Conta conta2 = new Investimento();
		Conta conta3 = new Poupanca();
		Conta conta4 = new Investimento();

		conta.setSaldo(UM_MIL);
		conta1.setSaldo(QUINHENTOS);
		conta2.setSaldo(SETECENTOS);

		Cliente cliente1 = new ClienteGold(NOME, CPF, SENHA);
		
		cliente1.vincularConta(conta1);
		cliente1.vincularConta(conta2);
		cliente1.listaConta.forEach(contas -> System.out.println(contas));
		System.out.println("______________________________________________");
		
		Cliente cliente2 = new ClienteGold(NOME, CPF, SENHA);		
		cliente2.vincularConta(conta2);			
		cliente2.listaConta.forEach(contas -> System.out.println(contas));

	}

}
//Em cliente dicionar metodo de associar mais uma conta a um cliente, um cliente pode possuir mais de uma conta