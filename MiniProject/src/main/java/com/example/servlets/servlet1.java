package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class servlet1 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				Connection con = connection_mysql.getConnection();
				if(con != null){
//					PreparedStatement preparedStatement =con.prepareStatement("select * from user");
//					ResultSet rc = preparedStatement.executeQuery();
//					while(rc.next())
//					{
//						System.out.println(rc.getString("name"));
//					}
					PreparedStatement pre =con.prepareStatement("insert into user values (? ,? ,?)");
					pre.setString(1, "a");
					pre.setString(2, "a@gmail.com");
					pre.setString(3, "a");
					pre.executeUpdate();pre.close();
					con.close();
					
				}else {
					System.out.println("khawi");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Exection");
			}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
