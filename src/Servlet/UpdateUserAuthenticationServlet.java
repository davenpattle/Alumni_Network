package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserAuthenticationList;
import Manager.UserAuthenticationManager;

public class UpdateUserAuthenticationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UpdateUserAuthenticationServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String user_name = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	try{
    		UserAuthenticationList ual = UserAuthenticationManager.getInstance().GetUser(user_name);
    		ual.setPassword(password);
    		System.out.println("Updating User Credentials");
    		UserAuthenticationManager.getInstance().UpdateUser(ual);
    		System.out.println("User Credentials Updated");
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		//System.out.println("User Credentials Updated");
    		out.println("User Credentials Updated");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
