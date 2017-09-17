package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserExpertiseList;
import Manager.UserExpertiseListManager;

public class DeleteUserExpertiseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public DeleteUserExpertiseServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String eid = request.getParameter("eid");

    	try{
    		UserExpertiseList uel = UserExpertiseListManager.getInstance().GetUserExpertiseByID(Integer.parseInt(uid), Integer.parseInt(eid));
    		
    		System.out.println("Deleting User Expertise");
    		UserExpertiseListManager.getInstance().DeleteUserExpertise(uel);
    		System.out.println("User Expertise Successfully Deleted");
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Expertise Successfully Deleted");
    		out.println("User Expertise Successfully Deleted");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
