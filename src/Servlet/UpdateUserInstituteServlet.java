package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseSchema.UserInstituteList;
import Manager.UserInstituteListManager;

public class UpdateUserInstituteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UpdateUserInstituteServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String iid = request.getParameter("iid");
    	String degree = request.getParameter("degree");
    	String sdate = request.getParameter("sdate");
    	String edate = request.getParameter("edate");

    	try{
    		UserInstituteList uil = UserInstituteListManager.getInstance().GetUserInstituteByID(Integer.parseInt(uid), Integer.parseInt(iid));
    		
    		if(degree!=null){
    			uil.setDegree(degree);
    		}
    		if(sdate!=null){
    			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MMM d, yyyy",Locale.ENGLISH);
    			DateTimeFormatter df2 = DateTimeFormatter.ofPattern("YYYY-MM-dd",Locale.ENGLISH);
    			uil.setStart_date(Date.valueOf(df2.format(LocalDate.parse(sdate, df1))));
    		}
    		if(edate!=null){
    			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MMM d, yyyy",Locale.ENGLISH);
    			DateTimeFormatter df2 = DateTimeFormatter.ofPattern("YYYY-MM-dd",Locale.ENGLISH);
    			uil.setEnd_date(Date.valueOf(df2.format(LocalDate.parse(edate, df1))));
    		}
    		
    		System.out.println("Updating User Institute");
    		UserInstituteListManager.getInstance().UpdateUserInstitute(uil);
    		System.out.println("User Institute Successfully Updating:" + uil.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Institute Successfully Updated");
    		out.println("User Institute Successfully Updated");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
