package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.ExpertiseList;
import FrontEndMap.ExpName;
import Manager.ExpertiseListManager;
import Manager.ObjectToJsonManager;

public class GetExpertisesByKeywordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetExpertisesByKeywordServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String keyword = request.getParameter("keyword");
    	
    	try{
    		System.out.println("Retrieving all expertise with "+ keyword);
    		List<ExpertiseList> expertise = ExpertiseListManager.getInstance().GetExpertisesByKeyword(keyword);
    		
    		List<ExpName> list = new ArrayList<>();
    		for(int i=0;i<expertise.size();i++){
    			ExpName e = new ExpName();
    			e.setE_id(expertise.get(i).getId());
    			e.setE_name(expertise.get(i).getE_name());
    			
    			list.add(e);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Expertises Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
