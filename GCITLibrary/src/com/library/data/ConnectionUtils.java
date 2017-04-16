package com.library.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con= DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/library", "root", "mysql");
		con.setAutoCommit(false);
		return con;
	}
}
