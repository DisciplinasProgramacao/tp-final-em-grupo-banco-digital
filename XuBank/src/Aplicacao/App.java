package Aplicacao;

import java.util.Scanner;

import Banco.*;
import Banco.Investimento;
import Cliente.*;

public class App {

	public static Banco banco = new Banco();
	static Scanner teclado = new Scanner(System.in);

	static void pausa(Scanner teclado) {
		System.out.println("Enter para continuar.");
		teclado.nextLine();
	}

	public static int menu(Scanner teclado) {
		System.out.println("--- Sistema Bancario ---");
		System.out.println("==========================");
		System.out.println("1 - Gerente");
		System.out.println("2 - Cliente");
		System.out.println("4 - Gerar relatorio");
		System.out.println("3 - Finalizar");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		return opcao;
	}

	private static void cadastroCliente() {
		

		System.out.println("Nome do cliente: ");
		String nome = teclado.nextLine();
		System.out.println("CPF do cliente: ");
		String cpf = teclado.nextLine();
		System.out.println("Senha: ");
		String senha = teclado.nextLine();
		System.out.println("Categoria do cliente:\n 1- VIP \n 2- Gold \n 3- Comum");
		int categoria = teclado.nextInt();

		Cliente cliente = new Cliente(nome, cpf, senha);

		try {
			if (categoria == 1) {
				banco.getListaCliente().add(new ClienteVip(nome, cpf, senha));
				System.out.println("Cliente Criado com sucesso!");
			} else if (categoria == 2) {
				banco.getListaCliente().add(new ClienteGold(nome, cpf, senha));
				System.out.println("Cliente Criado com sucesso!");
			} else {
				banco.getListaCliente().add(cliente);
				System.out.println("Cliente Criado com sucesso!");
			}
		} catch (NullPointerException e) {
			System.out.println("Problema ao cadastrar cliente!");
		}

	}

	private static boolean cpfIsValido(String Cpf) {
		return false;
	}

	private static void criarConta() {
		System.out.println("Tipo de conta: \n 1- Corrente \n 2- Poupanca \n 3- Renda Fixa\n 4- Investimento");
		int tipoConta = teclado.nextInt();
		switch (tipoConta) {
		case 1:
			banco.getListaContas().add(new Conta("Corrente"));
			System.out.println("Conta corrente Criada com sucesso!");
			break;
		case 2:
			banco.getListaContas().add(new Poupanca());
			System.out.println("Conta poupanca Criada com sucesso!");
			break;
		case 3:
			banco.getListaContas().add(new RendaFixa());
			System.out.println("Conta de renda fixa Criada com sucesso!");
			break;
		case 4:
			banco.getListaContas().add(new Investimento());
			System.out.println("Conta de investimento Criada com sucesso!");
			break;
		default:
			System.out.println("Algo deu errado, tente novamente!");
			break;
		}
	}

	private static void vincularCliente() {
		
		System.out.println("--- Vinculacao de conta ---");
		System.out.println("CPF do cliente: ");
		String CPF = teclado.nextLine();
		System.out.println("Numero da Conta: ");
		int numeroConta = teclado.nextInt();

		for (Cliente cliente : banco.getListaCliente()) {
			if (cliente.getCpf().equals(CPF)) {
				cliente.getListaConta().add(banco.getListaContas().get(numeroConta));
			}
		}

	}

	public static int menuGerente(Scanner teclado) {
		System.out.println("--- Sistema Bancario - Gerente ---");
		System.out.println("==========================");
		System.out.println("1 - Cadastrar cliente");
		System.out.println("2 - Criar conta");
		System.out.println("3 - vincular cliente");
		System.out.println("4 - sair");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		return opcao;
	}

	public static void sistemaGerente() {
		int opcao;
		Scanner teclado = new Scanner(System.in);
		do {
			opcao = menuGerente(teclado);
			switch (opcao) {

			case 1:
				cadastroCliente();
				break;
			case 2:
				criarConta();
				break;
			case 3:
				vincularCliente();
				break;
			case 4:
				opcao = 0;
				break;
			}
			pausa(teclado);
		} while (opcao != 0);
	}

	private static void sitemaCliente() {
		Scanner teclado = new Scanner(System.in);
//		Cliente cliente = identificaCliente();;
		int opcao;
		double valor = 0.00;
		
		do {
			opcao = menuCliente(teclado);
			switch (opcao) {

			case 1:
				identificaCliente().getListaConta().forEach(conta -> System.out.println("\n-----------------\nConta: " + conta.getTipo() + "\nSaldo da Conta: "+conta.getSaldo()));
				break;
			case 2:
				System.out.println("\nValor do saque:");
				valor = teclado.nextDouble();
				System.out.println("\nDigite o numero da conta uqe deseja realizar o saque: ");
				identificaCliente().getListaConta().get(teclado.nextInt()).saque(valor);
				break;
			case 3:
				System.out.println("\nValor do deposito:");
				valor = teclado.nextDouble();
				System.out.println("\nDigite o numero da conta uqe deseja realizar o saque: ");
				identificaCliente().getListaConta().get(teclado.nextInt()).saque(valor);
				break;
			case 4:
				System.out.println("Informe de redimentos nao disponivel no momento! :( ");
				break;
			case 5:
				opcao = 0;
				break;
			}
			pausa(teclado);
		} while (opcao != 0);
	}

	private static int menuCliente(Scanner teclado) {
		System.out.println("--- Sistema Bancario - Cliente ---");
		System.out.println("==========================");
		System.out.println("1 - Ver saldo");
		System.out.println("2 - Saque");
		System.out.println("3 - Deposito");
		System.out.println("4 - Informe de rendimentos");
		System.out.println("5 - Sair");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		return opcao;
	}
	
	private static Cliente identificaCliente() {
		System.out.println("Digite seu cpf: ");
		String cpf = teclado.nextLine();
		
		for(Cliente cliente: banco.getListaCliente()) {
			if(cliente.getCpf().equals(cpf)) {
				return cliente;				
			}
		}
			return null;
	}
	
	public static void main(String[] args) {

		int opcao;
		Scanner teclado = new Scanner(System.in);

		do {
			opcao = menu(teclado);

			switch (opcao) {
			case 1:
				sistemaGerente();
				break;
			case 2:
				sitemaCliente();
				break;
			case 3:
				System.out.println(banco);
				break;
			case 4:
				opcao = 0;
				break;
			}
			pausa(teclado);
		} while (opcao != 0);
		teclado.close();
	}

}
