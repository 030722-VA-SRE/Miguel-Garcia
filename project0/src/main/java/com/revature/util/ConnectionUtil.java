package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	//resuse this connection instead of creating more connections to the database
	private static Connection con;
	
	public static Connection getConnectionFromEnv() throws SQLException {
		/*-
		 * search for Edit the System environment variables
		 * 	- click on environment variables
		 * 	- under System variables, click new
		 * 	- enter a variable name, and its value
		 * 		- ie: DB_URL, [url_value]
		 * 	- press ok
		 * 
		 * NOTE: YOU MIGHT HAVE TO RESTART STS FOR YOUR NEW ENVIRONMENT VARIABLES TO LOAD!
		 */
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(url, username, password);
		}
		return con;
	}
	
}//end ConnectionUtil
