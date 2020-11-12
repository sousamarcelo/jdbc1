package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	//Abrindo a conex�o apartir do objeto criado do arquivo db.properties
	public static Connection getConnection() {
		if (conn == null) {
			try {
				System.out.println("Abrindo a conex�o");
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return null;
	}
	
	//fechando a conex�o
	public static void closeConnection() {
		if (conn != null) {
			try {
				System.out.println("Fechando a conex�o");
				conn.close();
				
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//carregando as informa��es do arquivo db.properties e transformando-os em objeto
	private static Properties loadProperties( ) {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
			
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

}
