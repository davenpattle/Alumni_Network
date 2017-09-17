package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserExpertiseList;
import Manager.UserExpertiseListManager;

public class UpdateUserExpertiseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UpdateUserExpertiseServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String eid = request.getParameter("eid");
    	String level = request.getParameter("level");


    	try{
    		UserExpertiseList uel = UserExpertiseListManager.getInstance().GetUserExpertiseByID(Integer.parseInt(uid), Integer.parseInt(eid));
    		
    		if(level!=null){
    			uel.setLevel(level);
    		}
    		
    		System.out.println("Updating User Expertise");
    		UserExpertiseListManager.getInstance().UpdateUserExpertise(uel);
    		System.out.println("User Company Successfully Updating:" + uel.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Expertise Successfully Updated");
    		out.println("User Expertise Successfully Updated");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
