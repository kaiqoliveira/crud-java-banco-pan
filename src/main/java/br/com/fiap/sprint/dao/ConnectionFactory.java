package br.com.fiap.sprint.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConexao() {
		String jdbc = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(jdbc, "rm87170", "220897");			
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
