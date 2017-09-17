package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserExpertiseList;
import Manager.ExpertiseListManager;
import Manager.UserExpertiseListManager;
import Manager.UserProfileManager;

public class AddUserExpertiseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddUserExpertiseServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String eid = request.getParameter("eid");
    	String level = request.getParameter("level");

    	UserExpertiseList uel = new UserExpertiseList();
    	uel.setU_id(UserProfileManager.getInstance().getUserByID(Integer.parseInt(uid)));
    	uel.setE_id(ExpertiseListManager.getInstance().getExpertiseByID(Integer.parseInt(eid)));
    	uel.setLevel(level);
    	
    	try{
    		System.out.println("Adding User Expertise");
    		UserExpertiseListManager.getInstance().AddUserExpertise(uel);
    		System.out.println("User Expertise Successfully Added:" + uel.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Expertise Successfully Added");
    		out.println("User Expertise Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
