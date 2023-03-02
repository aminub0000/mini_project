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


/**
 * Servlet implementation class Registre
 */
public class Registre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = connection_mysql.getConnection();
			if(con != null){
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");
				String re_pass = request.getParameter("re_pass");
				
				if (!name.isEmpty() || !email.isEmpty() || !pass.isEmpty() || !re_pass.isEmpty())
				{
					if (pass.equals(re_pass)) {
						PreparedStatement preparedStatement =con.prepareStatement("SELECT * FROM user WHERE name = ? OR email = ?");
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, email);
						ResultSet rc = preparedStatement.executeQuery();
						int count = 0;
						while(rc.next())
						{
							count++;
						}
						if (count==0) {
							PreparedStatement pre =con.prepareStatement("insert into user values (? ,? ,?)");
							pre.setString(1, name);
							pre.setString(2, email);
							pre.setString(3, pass);
							pre.executeUpdate();
							pre.close();
							con.close();
							request.setAttribute("nameL", name);
							request.setAttribute("passL", pass);
							
							request.setAttribute("message", "SUCCESS");
							request.getRequestDispatcher("login.jsp").forward(request, response);
							}
						else {
						    
						    request.setAttribute("message", "DUPLICAT");
						    request.getRequestDispatcher("registration.jsp").forward(request, response);
						}
						}
					else {
					    
					    request.setAttribute("message", "SAME_PASS");
					    request.getRequestDispatcher("registration.jsp").forward(request, response);
					}
				}
				else {
				    
				    request.setAttribute("message", "OBLIGATORY");
				    request.getRequestDispatcher("registration.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exection");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
