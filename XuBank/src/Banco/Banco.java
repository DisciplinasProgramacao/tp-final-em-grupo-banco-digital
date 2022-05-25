package Banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Cliente.Cliente;

public class Banco {
	
	private static List<Cliente> listaCliente = new LinkedList<>();
	private static List<Conta> listaContas = new LinkedList<>();
	
	public void gravaDadosEmArquivo() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("baseDeDados.txt", true))){
			
			bw.write(listaCliente.toString());
			
			
		} catch (IOException e) {
			System.out.println("Erro ao salvar arquivo"+e.getMessage());
		}
	}
	
	public double valorDeCapitalDoBanco() {
		 double capital =0;
		 
		 for(Conta conta: this.listaContas) {
			 capital += conta.getSaldo();
		 }
		return  capital;

	}
//	public void leDadosDeArquivo() {
//		try(BufferedReader br = new BufferedReader(new FileWriter("baseDeDados.txt", true))){
//			
//			
//			String linha = br.readLine();
//			new Cliente();
//			
//			
//		} catch (IOException e) {
//			System.out.println("Erro ao salvar arquivo"+e.getMessage());
//		}
//	}
	
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}
	
	public List<Conta> getListaContas() {
		return listaContas;
	}
	
	@Override
	public String toString() {
		StringBuilder linha = new StringBuilder();
		
		this.listaCliente.forEach(cliente -> linha.append(cliente));
		this.listaContas.forEach(conta -> linha.append(conta));		
		
		return linha.toString();
	}


	public static int quantidadeDeClientes() {		
		return listaCliente.size();
	}
	
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		Cliente cliente1 = new Cliente();
		
		Banco banco = new Banco();
		listaCliente.add(cliente);
		listaCliente.add(cliente1);
		banco.gravaDadosEmArquivo();
	}
}
