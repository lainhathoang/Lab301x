package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBService {
	
	private final String serverName = "localhost";
	private final String dbName = "Lab301x";
//	private final String dbTestName = "Lab301xTest";
	private final String userID = "root";
	private final String password = "1234";
	
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:mysql://" + serverName + "/" + dbName;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, userID, password);
	}
	
}
