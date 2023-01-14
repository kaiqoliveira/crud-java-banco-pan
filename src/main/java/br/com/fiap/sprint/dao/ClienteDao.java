package br.com.fiap.sprint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fiap.sprint.modelo.Cliente;

public class ClienteDao implements CRUD {
	

	
	public static void create(Cliente c) {
		String sql = "INSERT INTO tb_cliente(nome, sobrenome, email, telefone, cpf) VALUES(?, ?, ?, ?, ?)";
		try (Connection con = new ConnectionFactory().getConexao();
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] { "id" })) {

		
			pstmt.setString(1, c.getNome());
			pstmt.setString(2, c.getSobrenome());
			pstmt.setString(3, c.getEmail());
			pstmt.setString(4, c.getTelefone());
			pstmt.setString(5, c.getCpf());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<Cliente> find(String nome)throws Exception {
		
		
			String sql = "SELECT id, nome, sobrenome, cpf, email "
					+ "FROM tb_cliente WHERE lower(nome) like ? ORDER BY nome";
			List<Cliente> lista = null;
			try(Connection con = new ConnectionFactory().getConexao();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				pstmt.setString(1, "%" + nome.toLowerCase() + "%");
				
				ResultSet rs = pstmt.executeQuery();
				lista = new LinkedList<>();
				while (rs.next()) {
					Cliente c = new Cliente();
					c.setId(rs.getInt("id"));
					c.setNome(rs.getString("nome"));
					c.setSobrenome(rs.getString("sobrenome"));
					c.setCpf(rs.getString("cpf"));
					c.setEmail(rs.getString("email"));
					lista.add(c);				
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return lista;
		}
	
	public static Cliente findByPk(int clienteId) {
		String sql = String.format("SELECT * FROM tb_cliente WHERE id = %d ", clienteId);
		
		try {
			//Statement statement = connection.createStatement();
			//ResultSet resultSet = statement.executeQuery(sql);
			
			 Connection con = new ConnectionFactory().getConexao();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery(sql);
			 
			Cliente cliente = new Cliente();
			
			while (rs.next()) {
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCpf(rs.getString("cpf"));
				
		
			}
			
			System.out.println("--correct find by pk clientes");
			return cliente;
			
	} catch(SQLException e) {
		
			System.out.println("--incorrect find by pk clientes. " + e.getMessage());
			return null;
		}
	}
	
	public static void update(Cliente cliente) {
		String sql = "UPDATE clientes SET nome=?, cpf=?, nascimento=?, situacao=? WHERE id=?";
		 
		 try {
			 Connection con = new ConnectionFactory().getConexao();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 
			 pstmt.setString(1, cliente.getNome());
			 pstmt.setString(2, cliente.getSobrenome());
			 pstmt.setString(3, cliente.getEmail());
			 pstmt.setString(4, cliente.getTelefone());
			 pstmt.setString(5, cliente.getCpf());
		
		
			 pstmt.setInt(6, cliente.getId());
			 
			 pstmt.executeUpdate();
			 
			 System.out.println("--correct update on database");
			 
		 } catch(SQLException e) {
			 System.out.println("--incorrect update on database. " + e.getMessage());
		 }
	}
	
	public static void delete(int clienteId) {
		String sql = "DELETE FROM tb_cliente WHERE id = ?";
		
		try {
			 Connection con = new ConnectionFactory().getConexao();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, clienteId);
			pstmt.executeUpdate();
			
			System.out.println("--correct delete on cliente");
			
		} catch (SQLException e) {
			System.out.println("--incorrect delete on cliente. " + e.getMessage());
		}
	}
}
