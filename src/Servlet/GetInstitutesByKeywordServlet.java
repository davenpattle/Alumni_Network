package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.InstituteList;
import FrontEndMap.InstName;
import Manager.InstituteListManager;
import Manager.ObjectToJsonManager;

public class GetInstitutesByKeywordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetInstitutesByKeywordServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String keyword = request.getParameter("keyword");
    	
    	try{
    		System.out.println("Retrieving all institutes with "+ keyword);
    		List<InstituteList> institute = InstituteListManager.getInstance().GetInstitutesByKeyword(keyword);
    		
    		List<InstName> list = new ArrayList<>();
    		for(int i=0;i<institute.size();i++){
    			InstName s = new InstName();
    			s.setI_id(institute.get(i).getId());
    			s.setI_name(institute.get(i).getI_name());
    			s.setI_alias(institute.get(i).getI_alias());
    			
    			list.add(s);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Institutes Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
