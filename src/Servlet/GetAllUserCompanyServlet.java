package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserCompanyList;
import FrontEndMap.Company;
import Manager.ObjectToJsonManager;
import Manager.UserCompanyListManager;

public class GetAllUserCompanyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetAllUserCompanyServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	
    	try{
    		System.out.println("Retrieving all user companies");
    		List<UserCompanyList> company = UserCompanyListManager.getInstance().GetAllUserCompanies(Integer.parseInt(uid));
    		
    		List<Company> list = new ArrayList<>();
    		for(int i=0;i<company.size();i++){
    			Company c = new Company();
    			c.setC_id(company.get(i).getC_id().getId());
    			c.setCname(company.get(i).getC_id().getC_name());
    			c.setPosition(company.get(i).getPosition());
    			c.setS_date(company.get(i).getStart_date());
    			c.setE_date(company.get(i).getEnd_date());
    			
    			list.add(c);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Companies Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
