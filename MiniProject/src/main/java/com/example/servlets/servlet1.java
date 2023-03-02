package com.example.servlets;

import jakarta.servlet.RequestDispatcher;
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

import javax.swing.JOptionPane;

@WebServlet("servlet1")
public class servlet1 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		request.setAttribute("username",username);
		request.setAttribute("password",password);
		
		RequestDispatcher dispater = null;
		boolean find =false;
			try {
				if(request.getAttribute("username").equals("")
						||request.getAttribute("username").equals("")) {
					  response.getWriter().println("<script type=\"text/javascript\">");
					  response.getWriter().println("alert('fill information first');");
					  response.getWriter().println("location='login.jsp';");
					  response.getWriter().println("</script>");
					return;
				}else {
					Connection con = connection_mysql.getConnection();
					if(con != null){
						PreparedStatement preparedStatement =
								con.prepareStatement("select * from user");
						dispater =request.getRequestDispatcher("Home_page.jsp");
						ResultSet rc = preparedStatement.executeQuery();
						while(rc.next()) {
							if ( (rc.getString("name").equals(""+request.getAttribute("username")) 
									|| rc.getString("email").equals(""+request.getAttribute("username")) )
									&& rc.getString("password").equals(""+request.getAttribute("password")) ) {
									find = true;
									System.out.print("login successfuly for "+rc.getString("name")+" identified by "+rc.getString("password"));
									request.setAttribute("status","success");
									System.out.println("</br>success");
									dispater.forward(request, response);
							}
						}
						if(!find) {
							System.out.print("login faild");
							PrintWriter out = response.getWriter();
							 	out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			                   out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			                   out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			                   out.println("<script language=JavaScript>");
			                   out.println("$(document).ready(function(){");
			                   out.println("swal('welcome','successful!','success');");
			                   out.println("});");
			                   out.println("</script>");
							request.getRequestDispatcher("login.jsp").forward(request, response);
						}
						
						
					}else {
						System.out.println("khawi");
					}
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
