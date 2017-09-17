package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserProfile;
import Manager.UserProfileManager;

public class AddUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddUserServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String f_name = request.getParameter("fname");
    	String l_name = request.getParameter("lname");
    	String email = request.getParameter("email");
    	String location = request.getParameter("location");
    	String contact = request.getParameter("contact");
    	
    	UserProfile user = new UserProfile();
    	user.setF_name(f_name);
    	user.setL_name(l_name);
    	user.setEmail(email);
    	user.setLocation(location);
    	user.setContact(contact);
    	
    	try{
    		System.out.println("Adding User");
    		UserProfileManager.getInstance().AddUser(user);
    		System.out.println("User Successfully Added:" + user.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User ID: "+user.getId());
    		out.println(user.getId());
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
