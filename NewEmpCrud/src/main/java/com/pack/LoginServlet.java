package com.pack;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;
	       
	   
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//System.out.println("hi this is servlet");
			String us=request.getParameter("nam");
			String pw=request.getParameter("psw");
			
			System.out.println(us);
			
			//establishing connection 
			Connect j=null;
		
			try {
				j  = new Connect();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//getting password for particular username
			
			String p = "yuv";
			try {
				p = j.latest(us);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//if password matches it has to go to main page otherwise it must return to error page
			
			if(p.equals(pw)) {
				String key="cccccc"+us+p;
				System.out.println(key);
				
				HttpSession ses=request.getSession();
				ses.setAttribute("keyses", key);
						
				response.setHeader("keyyy", key);
				
				
				
				RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");
				rd.forward(request, response);
				
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("error.html");
				rd.forward(request, response);
				
			}
			

			
			
			
			
			
		}
	}


	


