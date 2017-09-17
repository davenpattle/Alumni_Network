package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserCompanyList;
import Manager.UserCompanyListManager;

public class DeleteUserCompanyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public DeleteUserCompanyServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String cid = request.getParameter("cid");

    	try{
    		UserCompanyList ucl = UserCompanyListManager.getInstance().GetUserCompanyByID(Integer.parseInt(uid), Integer.parseInt(cid));
    		
    		System.out.println("Deleting User Company");
    		UserCompanyListManager.getInstance().DeleteUserCompany(ucl);
    		System.out.println("User Company Successfully Deleted");
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Company Successfully Deleted");
    		out.println("User Company Successfully Deleted");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
