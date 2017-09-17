package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.CompanyList;
import Manager.CompanyListManager;

public class AddCompanyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddCompanyServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String name = request.getParameter("name");
    	String alias = request.getParameter("alias");

    	
    	CompanyList company = new CompanyList();
    	company.setC_name(name);
    	company.setC_alias(alias);
    	
    	try{
    		System.out.println("Adding Company");
    		CompanyListManager.getInstance().AddCompany(company);
    		System.out.println("Company Successfully Added:" + company.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Company ID: "+company.getId());
    		out.println("Company Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
