package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserProfile;
import FrontEndMap.UserData;
import Manager.ObjectToJsonManager;
import Manager.UserProfileManager;

public class GetUserAlumniServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetUserAlumniServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String userId = request.getParameter("userid");
    	
    	try{
    		System.out.println("Retrieving User Alumni");
    		
    		UserProfile user = UserProfileManager.getInstance().getUserByID(Integer.parseInt(userId));
    		
    		List<UserProfile> alumni = UserProfileManager.getInstance().GetUserAlumni(user);
    		
    		List<UserData> result = new ArrayList<>();
    		
    		for(int i=0;i<alumni.size();i++){
    			UserData u = new UserData();
    			u.setContact(alumni.get(i).getContact());
    			u.setEmail(alumni.get(i).getEmail());
    			u.setF_name(alumni.get(i).getF_name());
    			u.setL_name(alumni.get(i).getL_name());
    			u.setLocation(alumni.get(i).getLocation());
    			u.setUser_name("");
    			u.setUserid(alumni.get(i).getId());
    			
    			result.add(u);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Alumni Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(result));
    		out.close();
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
