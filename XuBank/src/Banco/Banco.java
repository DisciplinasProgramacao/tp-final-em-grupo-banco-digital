package Banco;

import java.util.LinkedList;
import java.util.List;

import Cliente.Cliente;

public class Banco {
	
	private static List<Cliente> listaCliente = new LinkedList<>();
	private static List<Conta> listaContas = new LinkedList<>();
	
	
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}
	
	public List<Conta> getListaContas() {
		return listaContas;
	}
	
	@Override
	public String toString() {
		StringBuilder linha = new StringBuilder();
		
		linha.append("\n----- Informacoes do banco ------\n");
		linha.append("------------ Contas ----------------");
		this.listaCliente.forEach(cliente -> linha.append(cliente));
		linha.append("------------ Clientes ----------------\n");
		this.listaContas.forEach(conta -> linha.append(conta));		
		
		return linha.toString();
	}
}
