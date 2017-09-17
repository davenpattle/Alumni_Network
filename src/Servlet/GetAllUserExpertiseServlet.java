package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserExpertiseList;
import FrontEndMap.Expertise;
import Manager.ObjectToJsonManager;
import Manager.UserExpertiseListManager;

public class GetAllUserExpertiseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetAllUserExpertiseServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	
    	try{
    		System.out.println("Retrieving all user expertises");
    		List<UserExpertiseList> expertise = UserExpertiseListManager.getInstance().GetAllUserExpertises(Integer.parseInt(uid));
    		
    		List<Expertise> list = new ArrayList<>();
    		for(int i=0;i<expertise.size();i++){
    			Expertise e = new Expertise();
    			e.setE_id(expertise.get(i).getE_id().getId());
    			e.setEname(expertise.get(i).getE_id().getE_name());
    			e.setLevel(expertise.get(i).getLevel());
    			
    			list.add(e);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Expertises Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
