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
import Manager.CompanyListManager;
import Manager.UserCompanyListManager;
import Manager.UserProfileManager;

public class AddUserCompanyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AddUserCompanyServlet() {
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

    	UserCompanyList ucl = new UserCompanyList();
    	ucl.setU_id(UserProfileManager.getInstance().getUserByID(Integer.parseInt(uid)));
    	ucl.setC_id(CompanyListManager.getInstance().getCompanyByID(Integer.parseInt(cid)));
    	ucl.setPosition(position);
    	DateTimeFormatter df1 = DateTimeFormatter.ofPattern("MMM d, yyyy",Locale.ENGLISH);
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("YYYY-MM-dd",Locale.ENGLISH);
		ucl.setStart_date(Date.valueOf(df2.format(LocalDate.parse(sdate, df1))));
    	//ucl.setStart_date(Date.valueOf(sdate));
		ucl.setEnd_date(Date.valueOf(df2.format(LocalDate.parse(edate, df1))));
		
    	
    	try{
    		System.out.println("Adding User Company");
    		UserCompanyListManager.getInstance().AddUserCompany(ucl);
    		System.out.println("User Company Successfully Added:" + ucl.toString());
    		
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		response.addHeader("Access-Control-Allow-Origin","*");
    		System.out.println("User Company Successfully Added");
    		out.println("User Company Successfully Added");
    		out.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
