package bank_servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Admin_change_details_status
 */
@WebServlet("/Admin_change_details_status")
public class Admin_change_details_status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_change_details_status() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		PrintWriter out = response.getWriter();
		
		String adminpin=request.getParameter("adminpin");
		if (adminpin.equals("Paras123@")) {
			
	
		Long ac= Long.parseLong(request.getParameter("acnumbervalue")) ;
//		out.print(ac);
		int i;
		if (currentstatus(ac)==0) {
			i=1;
		}
		else {
			i=0;
		}
		
		
		
		  
        String query = "UPDATE customers SET kyc = "+i+" WHERE acnumber ="+ac; // Adjust your table name and columns

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234")) {
            // Create a Statement
            Statement stmt = conn.createStatement();

            // Execute the query and get the result
            int rs = stmt.executeUpdate(query);
out.print(rs);

          if (rs!=0) {
        	  
        	  out.print("done settings for  a/c  "+ac);
        	  System.out.println("done settings for  a/c");
          }
          else {
        	  out.print("nothing changed");
        	  System.out.println("nothing changed");
        	 
          }
          
         
             
           
           RequestDispatcher rd= request.getRequestDispatcher("/admin");
           rd.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
		
//		if else start wala yha end kr ra hai 
		}
		else {
			RequestDispatcher rd= request.getRequestDispatcher("adminaction");
			response.setContentType("text/html");
			out.println("wrong password");
			rd.include(request, response);
		}
		
		
		
		
		
		
		
		
		
		
	}

	private int currentstatus(Long ac) {

		
	        int status=0;
//	        String query = "SELECT * FROM customers"; // Adjust your table name and columns
	        String query="SELECT kyc FROM customers where acnumber="+ac;
	        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234")) {
	            // Create a Statement
	            Statement stmt = conn.createStatement();
	            // Execute the query and get the result
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()) {
	            	

	             status = rs.getInt("kyc");
	            }
	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return status;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
