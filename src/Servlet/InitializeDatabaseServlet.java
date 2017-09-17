package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.InitializeDatabaseManager;

public class InitializeDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public InitializeDatabaseServlet() {
        super();
    }

    public void init() throws ServletException
    {
    	System.out.println("Servlet to initialize database and start indexer");
    	InitializeDatabaseManager.getInstance().InitializeDatabase();
    	System.out.println("Database Initialized");
    	InitializeDatabaseManager.getInstance().InitializeIndex();
    	System.out.println("Index Created");
    }
    
    public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException {
    	doPost(request, response);
	}
    
    public void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException {
    	
	}
}