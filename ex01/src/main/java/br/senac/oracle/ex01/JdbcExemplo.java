package br.senac.oracle.ex01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcDataSource;

public class JdbcExemplo {

	/*
	 * spring.datasource.driver-class-name=org.h2.Driver
	 * spring.datasource.platform=h2
	 * spring.datasource.url=@db.url@;LOCK_TIMEOUT=10000
	 * db.url=jdbc:h2:file:./data/dbxpto
	 * db.user=sa db.password=
	 * db.h2.console.enabled=false
	 */

	public Connection getConnection() throws SQLException {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setUrl("jdbc:h2:file:~/dbexemplo");
		ds.setUser("sa");
		ds.setPassword("");
		return ds.getConnection();
	}

	public void execute() {
		try (Connection c = getConnection()) {
			try (Statement stmt = c.createStatement()) {
				String sql = "CREATE TABLE IF NOT EXISTS TABELA1 " + "(id INTEGER auto_increment not NULL, " + " NOME VARCHAR(255), "
						+ " PRIMARY KEY ( id ))";
				stmt.execute(sql);
				
				sql = "INSERT INTO TABELA1 (NOME) VALUES ('Fulano'), ('Ciclana'), ('Beltrana')";
				stmt.execute(sql);
				
				sql = "SELECT ID, NOME FROM TABELA1";
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2));
				}
				
				rs.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		JdbcExemplo instance = new JdbcExemplo();
		instance.execute();

	}

}
