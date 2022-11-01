package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresTeste {
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	final private String host = "localhost";
	final private String user = "postgres";
	final private String passwd = "senha";
	
	public void readDataBase() throws Exception{
		try {
			Class.forName("org.postgresql.Driver");
			
			connect = DriverManager
					.getConnection("jdbc:postgresql://" + host + "/loja", user, passwd);
			
			statement = connect.createStatement();
			
			statement.executeUpdate("INSERT INTO clientes (nome, email)" + "VALUES ('GianLucas', 'gianlucas@gmail.com')");
			statement.executeUpdate("INSERT INTO clientes (nome, email)" + "VALUES ('Eulier', 'Eulier@gmail.com')");
			statement.executeUpdate("INSERT INTO clientes (nome, email)" + "VALUES ('Jose', 'Jose@gmail.com')");
			
			resultSet = statement.executeQuery("select * from clientes");
			writeResultSet(resultSet);
			System.out.println("Banco de Dados Conectado com Sucesso!");
			
		} catch (Exception e){
			throw e;
		} finally {
			close();
			
		}
		
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		
		while (resultSet.next()) {
			String codigo = resultSet.getString("codigo");
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
			
			System.out.println("codigo: " + codigo + "nome" + nome + "email" + email);
			
		}
	}

	
	private void close() {
		try {
			if (resultSet != null) {
				statement.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			
		}
	}
}
