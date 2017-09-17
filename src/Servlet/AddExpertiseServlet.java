package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.ExpertiseList;
import Manager.ExpertiseListManager;

public class AddExpertiseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddExpertiseServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String name = request.getParameter("name");

    	ExpertiseList expertise = new ExpertiseList();
    	expertise.setE_name(name);
    	
    	try{
    		System.out.println("Adding Expertise");
    		ExpertiseListManager.getInstance().AddExpertise(expertise);
    		System.out.println("Expertise Successfully Added:" + expertise.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Expertise ID: "+expertise.getId());
    		out.println("Expertise Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
