package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserAuthenticationList;
import DatabaseSchema.UserProfile;
import Manager.UserAuthenticationManager;
import Manager.UserProfileManager;

public class RegisterUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RegisterUserServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String u_id = request.getParameter("uid");
    	String user_name = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	UserAuthenticationList ual = new UserAuthenticationList();
    	try{
    		System.out.println("Registering User");
    		UserAuthenticationList u = UserAuthenticationManager.getInstance().CheckUserNameAvailability(user_name);
    		
    		if(u==null){
    			UserProfile user = UserProfileManager.getInstance().getUserByID(Integer.parseInt(u_id));
    	
    			ual.setU_id(user);
    			ual.setUser_name(user_name);
    			ual.setPassword(password);
    		
    			UserAuthenticationManager.getInstance().RegisterUser(ual);
    			System.out.println("User Successfully Registered");
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		response.addHeader("Access-Control-Allow-Origin","*");
	    		System.out.println("User Registered Successfully");
	    		out.println("User Registered Successfully");
	    		out.close();
    		}
    		else{
    			System.out.println("User with specified UserName already exists");
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		response.addHeader("Access-Control-Allow-Origin","*");
	    		System.out.println("User with specified UserName already exists");
	    		out.println("0");
	    		out.close();
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
