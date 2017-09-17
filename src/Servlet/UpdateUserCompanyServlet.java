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

import DatabaseSchema.UserCompanyList;
import Manager.UserCompanyListManager;

public class UpdateUserCompanyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UpdateUserCompanyServlet() {
        super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Do nothing
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uid = request.getParameter("uid");
    	String cid = request.getParameter("cid");
    	String position = request.getParameter("position");
    	String sdate = request.getParameter("sdate");
    	String edate = request.getParameter("edate");

    	try{
    		UserCompanyList ucl = UserCompanyListManager.getInstance().GetUserCompanyByID(Integer.parseInt(uid), Integer.parseInt(cid));
    		
    		if(position!=null){
    			ucl.setPosition(position);
    		}
    		if(sdate!=null){
    			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MMM d, yyyy",Locale.ENGLISH);
    			DateTimeFormatter df2 = DateTimeFormatter.ofPattern("YYYY-MM-dd",Locale.ENGLISH);
    			ucl.setStart_date(Date.valueOf(df2.format(LocalDate.parse(sdate, df1))));
    		}
    		if(edate!=null){
    			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MMM d, yyyy",Locale.ENGLISH);
    			DateTimeFormatter df2 = DateTimeFormatter.ofPattern("YYYY-MM-dd",Locale.ENGLISH);
    			ucl.setEnd_date(Date.valueOf(df2.format(LocalDate.parse(edate, df1))));
    		}
    		
    		System.out.println("Updating User Company");
    		UserCompanyListManager.getInstance().UpdateUserCompany(ucl);
    		System.out.println("User Company Successfully Updating:" + ucl.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Company Successfully Updated");
    		out.println("User Company Successfully Updated");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
