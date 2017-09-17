package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.InstituteList;
import Manager.InstituteListManager;

public class AddInstituteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddInstituteServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String name = request.getParameter("name");
    	String alias = request.getParameter("alias");

    	
    	InstituteList institute = new InstituteList();
    	institute.setI_name(name);
    	institute.setI_alias(alias);
    	
    	try{
    		System.out.println("Adding Institute");
    		InstituteListManager.getInstance().AddInstitute(institute);
    		System.out.println("Institute Successfully Added:" + institute.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Institute ID: "+ institute.getId());
    		out.println("Institute Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
