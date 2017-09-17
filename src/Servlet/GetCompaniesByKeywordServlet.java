package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.CompanyList;
import FrontEndMap.CmpName;
import Manager.CompanyListManager;
import Manager.ObjectToJsonManager;

public class GetCompaniesByKeywordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetCompaniesByKeywordServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String keyword = request.getParameter("keyword");
    	
    	try{
    		System.out.println("Retrieving all companies with "+ keyword);
    		List<CompanyList> company = CompanyListManager.getInstance().GetCompaniesByKeyword(keyword);
    		
    		List<CmpName> list = new ArrayList<>();
    		for(int i=0;i<company.size();i++){
    			CmpName c = new CmpName();
    			c.setC_id(company.get(i).getId());
    			c.setC_name(company.get(i).getC_name());
    			c.setC_alias(company.get(i).getC_alias());
    			
    			list.add(c);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Companies Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
