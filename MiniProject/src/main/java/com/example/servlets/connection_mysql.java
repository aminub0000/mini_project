package com.example.servlets;
import java.sql.*;
import java.sql.Connection;

public class connection_mysql {
	

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
			String url_pattern="jdbc:mysql://sql8.freesqldatabase.com:3306/";
			String name_dataase="sql8602322";
			String pass="zH3KIEQ7jC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection =DriverManager.getConnection(url_pattern+name_dataase ,name_dataase ,pass);
			return connection;
	}

}
