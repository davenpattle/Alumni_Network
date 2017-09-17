package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.JobList;
import FrontEndMap.Job;
import Manager.JobListManager;
import Manager.ObjectToJsonManager;

public class GetJobServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GetJobServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String c_id = request.getParameter("cid");
    	String exp = request.getParameter("exp");
    	
    	try{
    		System.out.println("Retrieving all jobs");
    		List<JobList> jobs = JobListManager.getInstance().GetAllJobs(Integer.parseInt(c_id),Integer.parseInt(exp));
    		
    		List<Job> list = new ArrayList();
    		for(int i=0;i<jobs.size();i++)
    		{
    			Job j = new Job();
    			j.setJob_id(jobs.get(i).getId());
    			j.setCategory(jobs.get(i).getCategory().getCategory());
    			j.setCompany(jobs.get(i).getC_id().getC_name());
    			j.setDescription(jobs.get(i).getDescription());
    			j.setLocation(jobs.get(i).getLocation());
    			j.setMax_exp(jobs.get(i).getMaxexp());
    			j.setMin_exp(jobs.get(i).getMinexp());
    			j.setType(jobs.get(i).getType());
    			
    			list.add(j);
    		}
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("Jobs Retrieved");
    		out.println(ObjectToJsonManager.getInstance().ConvertToJson(list));
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
