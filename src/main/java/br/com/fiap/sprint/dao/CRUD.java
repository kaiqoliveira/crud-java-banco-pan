package br.com.fiap.sprint.dao;

import java.util.List;

import br.com.fiap.sprint.modelo.Cliente;

public interface CRUD {
	
	public static void create(Cliente cliente) {};
	public static void delete(int clienteId) {};
	public static List<Cliente> find(String pesquisa){return null;}
	public static Cliente findByPk(int clienteId) {return null;}
	public static void update(Cliente cliente) {}
}
