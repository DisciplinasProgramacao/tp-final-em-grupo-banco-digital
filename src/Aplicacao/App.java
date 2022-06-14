package Aplicacao;

import Banco.Banco;
import Cliente.Cliente;
import Cliente.Gold;
import Cliente.Vip;
import Banco.Conta;
import Banco.Poupanca;
import Banco.RendaFixa;
import Banco.Investimento;


import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        menu();
    }


    public static void menu() throws FileNotFoundException {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Gerente");
            System.out.println("2 - Cliente");
            System.out.println("0 - SAIR");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1: gerente();break;
                case 2: cliente();break;
                case 0: break;
            }
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            menu();
        }


    }


    public static void gerente() throws FileNotFoundException {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - ATIVAR CONTA");
            System.out.println("2 - RELATORIO VALOR TOTAL CUSTODIA");
            System.out.println("3 - VALOR MEDIO POR TIPO DE CONTA");
            System.out.println("4 - CLIENTE EXTREMO POR TIPO DE CONTA");
            System.out.println("9 - VOLTAR MENU");
            System.out.println("0 - Sair ");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1: pesquisarCliente();break;
                case 2: totalCustodia();gerente();break;
                case 3: media();gerente();break;
                case 4: extremo();gerente();break;
                case 9:menu();break;
                case 0: break;
            }
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            gerente();
        }


    }

    public static void pesquisarCliente() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        Banco xuBank = new Banco();
        System.out.println("1 - CLIENTE NÃO EXISTENTE");
        System.out.println("2 - CLIENTE JÁ EXISTENTE");
        System.out.println("9 - VOLTAR MENU ANTERIOR ");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                criarCliente();
                gerente();
                break;
            case 2:
                sc.nextLine();
                System.out.println("FAVOR INFORMAR CPF ");
                String cpf = sc.nextLine();
                Cliente clinte = xuBank.buscarCliente(cpf);
                System.out.println(clinte);
                clinte.addConta(criarConta());
                xuBank.salvarDados();
                gerente();
                break;
            case 9:
                gerente();
                break;

        }

    }


    public static void criarCliente() throws FileNotFoundException {
        Banco xubank = new Banco();
        Scanner sc = new Scanner(System.in);
        System.out.println("NOME DO CLIENTE");
        String nome = sc.nextLine();
        System.out.println("CPF DO CLIENTE");
        String cpf = sc.nextLine();
        System.out.println("SENHA DE ACESSO");
        String senha = sc.nextLine();
        System.out.println("CLASSIFICAÇÃO DO CLIENTE:\n" + "1 - REGULAR\n" + "2 - GOLD\n" + "3 - VIP");
        System.out.println("9 - VOLTAR MENU ANTERIOR ");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                xubank.addCliente(new Cliente(nome, cpf, senha));
                break;
            case 2:
                xubank.addCliente(new Cliente(nome, cpf, senha, new Gold()));
                break;
            case 3:
                xubank.addCliente(new Cliente(nome, cpf, senha, new Vip()));
                break;
            case 9:
                pesquisarCliente();
                break;
        }
    }


    public static void extremo() throws FileNotFoundException {
        System.out.println("QUAL TIPO DE CONTA DESEJA SABER");
        int opcao = menuContas();
        switch (opcao) {
            case 1: condiçãoExtremo("REGULAR");break;
            case 2: condiçãoExtremo("POUPANÇA");break;
            case 3: condiçãoExtremo("RENDA FIXA");break;
            case 4: condiçãoExtremo("INVESTIMENTO");break;
        }
    }


    public static void condiçãoExtremo(String tipo) throws FileNotFoundException {
        Banco xubank = new Banco();
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - EXTREMO MINIMO");
        System.out.println("2 - EXTREMO MAXIMO");
        System.out.println("9 - VOLTA");
        System.out.println("0 - SAIR");
        int opção = sc.nextInt();
        switch (opção) {
            case 1: System.out.println(xubank.calcExtremoMin(tipo));break;
            case 2: System.out.println(xubank.calcExtremoMAx(tipo));break;
            case 9: gerente();break;
            case 0: break;
        }


    }


    public static void media() throws FileNotFoundException {
        Banco xubank = new Banco();
        System.out.println("QUAL TIPO DE CONTA DESEJA SABER");
        int opcao = menuContas();
        switch (opcao) {
            case 1: System.out.println(xubank.calcMedia("REGULAR"));break;
            case 2: System.out.println(xubank.calcMedia("POUPANÇA"));break;
            case 3: System.out.println(xubank.calcMedia("RENDA FIXA"));break;
            case 4: System.out.println(xubank.calcMedia("INVESTIMENTO"));break;
        }
    }

    public static void totalCustodia() throws FileNotFoundException {
        Banco xubank = new Banco();
        System.out.println("QUAL TIPO DE CONTA DESEJA SABER");
        int opcao = menuContas();
        switch (opcao) {
            case 1: System.out.println(xubank.calcCustodia("REGULAR"));break;
            case 2: System.out.println(xubank.calcCustodia("POUPANÇA"));break;
            case 3: System.out.println(xubank.calcCustodia("RENDA FIXA"));break;
            case 4: System.out.println(xubank.calcCustodia("INVESTIMENTO"));break;
        }
    }


    public static int menuContas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - CONTA REGULAR");
        System.out.println("2 - CONTA POUPANÇA");
        System.out.println("3 - CONTA RENDA FIXA");
        System.out.println("4 - CONTA INVESTIMENTO");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
            case 4: return 4;
        }
        return -1;
    }


    public static Conta criarConta() {
        System.out.println("QUAL TIPO DE CONTA DESEJA CRIAR?");
        int opcao = menuContas();
        switch (opcao) {
            case 1: Conta regular = new Conta();
                System.out.println(regular);return regular;
            case 2: Conta poupanca = new Poupanca();
                System.out.println(poupanca);return poupanca;
            case 3: Conta rendaFixa = new RendaFixa();
                System.out.println(rendaFixa);return rendaFixa;
            case 4: Conta investimento = new Investimento();
                System.out.println(investimento) ;return investimento;
        }
        return null;
    }


    public static void cliente() throws FileNotFoundException {
        try {
            Banco xubank = new Banco();
            Scanner sc = new Scanner(System.in);
            System.out.println("FAVOR INFORMAR CPF");
            String cpf = sc.nextLine();
            Cliente cliente = xubank.buscarCliente(cpf);
            System.out.println("1 - SALDO");
            System.out.println("2 - DEPOSITO");
            System.out.println("3 - SAQUE");
            System.out.println("4 - EXTRATO");
            System.out.println("0 - SAIR");
            System.out.println("9 - VOLTAR MENU ANTERIOR");
            int opcao = sc.nextInt();
            System.out.println("FAVOR INFORMAR N° CONTA");
            int numConta = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("SALDO: " + cliente.buscarConta(numConta).getSaldo());
                    xubank.salvarDados();
                    menu();
                    break;
                case 2:
                    System.out.println("INFORME O VALOR DO DEPOSITO: ");
                    double valor = sc.nextDouble();
                    cliente.buscarConta(numConta).deposito(valor);
                    cliente.getCategoria().calcBonusPontos(cliente.valorTotal());
                    System.out.println("OPERAÇÃO REALIZADA COM SUCESSO");
                    xubank.salvarDados();
                    menu();
                    break;
                case 3:
                    System.out.println("INFORME O VALOR DO SAQUE: ");
                    double saque = sc.nextDouble();
                    cliente.buscarConta(numConta).saque(saque);
                    System.out.println("OPERAÇÃO REALIZADA COM SUCESSO");
                    xubank.salvarDados();
                    menu();
                    break;
                case 4:
                    System.out.println(cliente.buscarConta(numConta).toString());
                    xubank.salvarDados();
                    menu();
                case 9:
                    menu();
                    break;
                case 0:
                    break;
            }
        } catch (NullPointerException nex) {
            System.out.println("NAO EXISTE Nº DE CONTA PARA ESSE CLIENTE");
            cliente();
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITAR SOMENTE NUMEROS");
            cliente();
        }


    }

}
