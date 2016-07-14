package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String POSTGRE_DRIVER = "org.postgresql.Driver";
	
	private static String url = "";
	
	private static String login = "postgres";
	
	private static String senha = "";
	
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        String tpBanco = "0";
        if ("0".equals(tpBanco)) {
        	url = "jdbc:postgresql://localhost:5432/simon";
        	
        }
        Class.forName(POSTGRE_DRIVER);
        return DriverManager.getConnection(url, login, senha);
    }
}
