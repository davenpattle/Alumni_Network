package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserInstituteList;
import FrontEndMap.Institute;
import Manager.ObjectToJsonManager;
import Manager.UserInstituteListManager;

public class GetAllUserInstituteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetAllUserInstituteServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	
    	try{
    		System.out.println("Retrieving all user institutes");
    		List<UserInstituteList> institute = UserInstituteListManager.getInstance().GetAllUserInstitutes(Integer.parseInt(uid));
    		
    		List<Institute> list = new ArrayList<>();
    		for(int i=0;i<institute.size();i++){
    			Institute s = new Institute();
    			s.setI_id(institute.get(i).getI_id().getId());
    			s.setIname(institute.get(i).getI_id().getI_name());
    			s.setDegree(institute.get(i).getDegree());
    			s.setS_date(institute.get(i).getStart_date());
    			s.setE_date(institute.get(i).getEnd_date());
    			
    			list.add(s);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Institutes Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
