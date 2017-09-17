package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserAuthenticationList;
import DatabaseSchema.UserProfile;
import FrontEndMap.UserData;
import Manager.ObjectToJsonManager;
import Manager.UserAuthenticationManager;

public class UserAuthenticationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UserAuthenticationServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String user_name = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	try{
    		System.out.println("Verifying User");
    		UserAuthenticationList ual = UserAuthenticationManager.getInstance().VerifyUser(user_name, password);
    		
    		if(ual==null){
    			System.out.println("UserName/Password specified is wrong");
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		response.addHeader("Access-Control-Allow-Origin","*");
	    		out.println("0");
	    		out.close();
    		}
    		else{
    			System.out.println("User Verified Successfuly");
	    		response.setContentType("text/html");
	    		PrintWriter out = response.getWriter();
	    		response.addHeader("Access-Control-Allow-Origin","*");
	    		UserProfile user = ual.getU_id();
	    		
	    		UserData data = new UserData();
	    		data.setF_name(user.getF_name());
	    		data.setL_name(user.getL_name());
	    		data.setEmail(user.getEmail());
	    		data.setLocation(user.getLocation());
	    		data.setContact(user.getContact());
	    		data.setUserid(ual.getU_id().getId());
	    		data.setUser_name(ual.getUser_name());
	    		
	    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(data));
	    		out.close();
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
