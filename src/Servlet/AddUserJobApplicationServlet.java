package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.JobApplicantList;
import Manager.JobListManager;
import Manager.UserProfileManager;

public class AddUserJobApplicationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddUserJobApplicationServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String j_id = request.getParameter("jid");
    	String u_id = request.getParameter("uid");
    	
    	try{
    		JobApplicantList applicant = new JobApplicantList();
    		
    		applicant.setJob_id(JobListManager.getInstance().GetJobById(Integer.parseInt(j_id)));
    		applicant.setUser_id(UserProfileManager.getInstance().getUserByID(Integer.parseInt(u_id)));
    		
    		JobListManager.getInstance().ApplyJobForUser(applicant);
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Application Successfully Added");
    		out.println("User Application Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
