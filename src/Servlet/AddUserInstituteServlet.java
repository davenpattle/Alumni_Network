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
import Manager.InstituteListManager;
import Manager.UserInstituteListManager;
import Manager.UserProfileManager;

public class AddUserInstituteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddUserInstituteServlet() {
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

    	UserInstituteList uil = new UserInstituteList();
    	uil.setU_id(UserProfileManager.getInstance().getUserByID(Integer.parseInt(uid)));
    	uil.setI_id(InstituteListManager.getInstance().getInstituteByID(Integer.parseInt(iid)));
    	uil.setDegree(degree);
    	DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MMM d, yyyy",Locale.ENGLISH);
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("YYYY-MM-dd",Locale.ENGLISH);
		uil.setStart_date(Date.valueOf(df2.format(LocalDate.parse(sdate, df1))));
    	//ucl.setStart_date(Date.valueOf(sdate));
		uil.setEnd_date(Date.valueOf(df2.format(LocalDate.parse(edate, df1))));
    	
    	try{
    		System.out.println("Adding User Institute");
    		UserInstituteListManager.getInstance().AddUserInstitute(uil);
    		System.out.println("User Institute Successfully Added:" + uil.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Institute Successfully Added");
    		out.println("User Institute Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
