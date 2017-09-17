package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserProfile;
import Manager.UserProfileManager;

public class UpdateUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UpdateUserServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String u_id = request.getParameter("uid");
    	String f_name = request.getParameter("fname");
    	String l_name = request.getParameter("lname");
    	String email = request.getParameter("email");
    	String location = request.getParameter("location");
    	String contact = request.getParameter("contact");
    	
    	UserProfile user = null;
    	
    	try{
    		System.out.println("Retrieving User for Updation");
    		user = UserProfileManager.getInstance().getUserByID(Integer.parseInt(u_id));
    		if(f_name!=null){
    			user.setF_name(f_name);
    		}
    		if(l_name!=null){
    			user.setL_name(l_name);
    		}
    		if(email!=null){
    			user.setEmail(email);
    		}
    		if(location!=null){
    			user.setLocation(location);
    		}
    		if(contact!=null){
    			user.setContact(contact);
    		}
    		System.out.println("Updating User");
    		UserProfileManager.getInstance().UpdateUser(user);
    		System.out.println("User Successfully Updated:" + user.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Profile Successfully Updated");
    		out.println("User Profile Successfully Updated");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
