package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserInstituteList;
import Manager.UserInstituteListManager;

public class DeleteUserInstituteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public DeleteUserInstituteServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String iid = request.getParameter("iid");

    	try{
    		UserInstituteList uil = UserInstituteListManager.getInstance().GetUserInstituteByID(Integer.parseInt(uid), Integer.parseInt(iid));
    		
    		System.out.println("Deleting User Institute");
    		UserInstituteListManager.getInstance().DeleteUserInstitute(uil);
    		System.out.println("User Institute Successfully Deleting");
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Institute Successfully Deleted");
    		out.println("User Institute Successfully Deleted");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
