package Aplicacao;

import Banco.*;
import Cliente.Cliente;
import Cliente.Gold;
import Cliente.Vip;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws FileNotFoundException,Validation {
        Banco xu = new Banco();
        System.out.println(xu.percorrerList());
            menu();
    }


    public static void menu() throws FileNotFoundException,Validation {
        System.out.println();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - GERENTE");
            System.out.println("2 - CLIENTE");
            System.out.println("0 - SAIR");
            int opcao = sc.nextInt();
            limparTela();
            switch (opcao) {
                case 1: gerente();break;
                case 2: cliente();break;
                case 0:System.out.println("PROGRAMA FINALIZADO"); break;
            }
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            menu();
        }
    }


    public static void gerente() throws FileNotFoundException,Validation {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - ATIVAR CONTA");
            System.out.println("2 - RELATORIO VALOR TOTAL CUSTODIA");
            System.out.println("3 - VALOR MEDIO POR TIPO DE CONTA");
            System.out.println("4 - CLIENTE EXTREMO POR TIPO DE CONTA");
            System.out.println("9 - VOLTAR MENU");
            System.out.println("0 - Sair ");
            int opcao = sc.nextInt();
            limparTela();
            switch (opcao) {
                case 1: pesquisarCliente();break;
                case 2: totalCustodia();gerente();break;
                case 3: media();gerente();break;
                case 4: extremo();gerente();break;
                case 9: menu();break;
                case 0: System.out.println("PROGRAMA FINALIZADO"); break;
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
        try {
            int opcao = sc.nextInt();
            limparTela();
            switch (opcao) {
                case 1:
                    criarCliente();
                    gerente();
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("FAVOR INFORMAR CPF ");
                    String cpf = sc.nextLine();
                    Cliente cliente = xuBank.buscarCliente(cpf);
                    System.out.println(cliente);
                    Conta conta = criarConta();
                    if (cliente.verificarCheque() && conta.getDescricao().equals("INVESTIMENTO")) {
                        System.out.println("CLIENTE REGULAR NÃO PODE TER CONTA INVESTIMENTO.");
                        pesquisarCliente();
                    } else {
                            xuBank.eguals(cliente,conta.getDescricao());
                            cliente.addConta(conta);
                            System.out.println(conta.toString());
                            xuBank.salvarDados();
                            System.out.println("CONTA CRIADA COM SUCESSO!!");
                            gerente();
                            break;
                    }
                case 9:
                    gerente();
                    break;
            }
        }catch (Validation vex){
            System.out.println(vex.getMessage());
            pesquisarCliente();
        }
    }




    public static void criarCliente() throws FileNotFoundException {
        System.out.println("================ CRIAR CLIENTE ================");
        Banco xubank = new Banco();
        Scanner sc = new Scanner(System.in);
        System.out.println("NOME DO CLIENTE");
        String nome = validarCampoVazio(sc.nextLine());
        System.out.println("CPF DO CLIENTE");
        String cpf = validarCampoVazio(sc.nextLine());
        System.out.println("SENHA DE ACESSO");
        String senha = validarCampoVazio(sc.nextLine());
        System.out.println("CLASSIFICAÇÃO DO CLIENTE:\n" + "1 - REGULAR\n" + "2 - GOLD\n" + "3 - VIP");
        System.out.println("9 - VOLTAR MENU ANTERIOR ");
        int opcao = sc.nextInt();
        try {
            switch (opcao) {
                case 1: xubank.addCliente(new Cliente(nome, cpf, senha));break;
                case 2: xubank.addCliente(new Cliente(nome, cpf, senha, new Gold()));break;
                case 3: xubank.addCliente(new Cliente(nome, cpf, senha, new Vip()));break;
                case 9: pesquisarCliente();break;
            }
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            criarCliente();
        }

    }


    public static void extremo() throws FileNotFoundException, Validation {
        System.out.println("================ EXTREMO ================");
        System.out.println("QUAL TIPO DE CONTA DESEJA SABER");
        int opcao = menuContas();
        try {
            switch (opcao) {
                case 1: condiçãoExtremo("CORRENTE");break;
                case 2: condiçãoExtremo("POUPANÇA");break;
                case 3: condiçãoExtremo("RENDA FIXA");break;
                case 4: condiçãoExtremo("INVESTIMENTO");break;
            }
        }catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            extremo();
        }

    }


    public static void condiçãoExtremo(String tipo) throws FileNotFoundException, Validation {
        System.out.println("================ CONDIÇÃO EXTREMO ================");
        Banco xubank = new Banco();
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - EXTREMO MINIMO");
        System.out.println("2 - EXTREMO MAXIMO");
        System.out.println("9 - VOLTA");
        System.out.println("0 - SAIR");
        try {
            int opção = sc.nextInt();
            switch (opção) {
                case 1: System.out.println(xubank.calcExtremoMin(tipo));break;
                case 2: System.out.println(xubank.calcExtremoMAx(tipo));break;
                case 9: gerente();break;
                case 0: break;
            }
        }catch (InputMismatchException iex) {
        System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            condiçãoExtremo(tipo);
    }

}



    public static void media() throws FileNotFoundException {
        System.out.println("================ MEDIA ================");
        Banco xubank = new Banco();
        System.out.println("QUAL TIPO DE CONTA DESEJA SABER");
        int opcao = menuContas();
        try{
            switch (opcao) {
                case 1: System.out.println(xubank.calcMedia("CORRENTE"));break;
                case 2: System.out.println(xubank.calcMedia("POUPANÇA"));break;
                case 3: System.out.println(xubank.calcMedia("RENDA FIXA"));break;
                case 4: System.out.println(xubank.calcMedia("INVESTIMENTO"));break;
            }
        }catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            media();
        }

    }




    public static void totalCustodia() throws FileNotFoundException {
        System.out.println("================ TATOL CUSTODIA ================");
        Banco xubank = new Banco();
        System.out.println("QUAL TIPO DE CONTA DESEJA SABER");
        try{
            int opcao = menuContas();
            switch (opcao) {
                case 1: System.out.println(xubank.calcCustodia("CORRENTE"));break;
                case 2: System.out.println(xubank.calcCustodia("POUPANÇA"));break;
                case 3: System.out.println(xubank.calcCustodia("RENDA FIXA"));break;
                case 4: System.out.println(xubank.calcCustodia("INVESTIMENTO"));break;
            }
        }catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            totalCustodia();
        }

    }


    public static int menuContas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - CONTA CORRENTE");
        System.out.println("2 - CONTA POUPANÇA");
        System.out.println("3 - CONTA RENDA FIXA");
        System.out.println("4 - CONTA INVESTIMENTO");
        int opcao = sc.nextInt();
        try{
            switch (opcao) {
                case 1: return 1;
                case 2: return 2;
                case 3: return 3;
                case 4: return 4;
            }
        }catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            menuContas();
        }

        return -1;
    }


    public static Conta criarConta() {
        System.out.println("================ CRIAR CONTA ================");
        System.out.println("QUAL TIPO DE CONTA DESEJA CRIAR?");
        int opcao = menuContas();
        try {
            switch (opcao) {
                case 1: Conta corrente = new Conta();return corrente;
                case 2: Conta poupanca = new Poupanca();return poupanca;
                case 3: Conta rendaFixa = new RendaFixa();return rendaFixa;
                case 4: Conta investimento = new Investimento();return investimento;
            }
        }catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITA SOMENTE O NUMERO DA OPÇÃO");
            menuContas();
        }

        return null;
    }


    public static void cliente() throws FileNotFoundException, Validation {
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
                    if(saque > cliente.buscarConta(numConta).getSaldo()){
                        if(cliente.verificarCheque()){
                            System.out.println("SALDO INSUFICIENTE");
                            menu();
                        }else{
                            cliente.limiteDisponivel(numConta);
                            cliente.buscarConta(numConta).saque(saque);
                            System.out.println("OPERAÇÃO REALIZADA COM SUCESSO");
                            xubank.salvarDados();
                            menu();
                        }
                    }else{
                    cliente.buscarConta(numConta).saque(saque);
                    System.out.println("OPERAÇÃO REALIZADA COM SUCESSO");
                    xubank.salvarDados();
                    menu();
                    }break;
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
        } catch (Validation vex){
            System.out.println(vex.getMessage());
        } catch (NullPointerException nex) {
            System.out.println("NAO EXISTE Nº DE CONTA PARA ESSE CLIENTE");
            cliente();
        } catch (InputMismatchException iex) {
            System.out.println("FAVOR DIGITAR SOMENTE NUMEROS");
            cliente();
        }

    }


    public static String validarCampoVazio(String campo) {
        Scanner sc = new Scanner(System.in);
        if (campo.equals("")) {
            System.out.println("FAVOR PREENCHER O COMPO QUE SE ENCONTRA VAZIO");
            return validarCampoVazio(sc.nextLine());
        } else {
            return campo;
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 33; i++) {
            System.out.println("");
        }
    }


    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

}
