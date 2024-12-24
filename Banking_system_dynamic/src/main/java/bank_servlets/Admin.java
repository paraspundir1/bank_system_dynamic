package bank_servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.sql.Statement;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String so=null;
		if (session.getAttribute("datacount")==null) {
			session.setAttribute("datacount", "all");
			System.out.println("helo");
			so="all";
		}else {
			
		 so=(String) session.getAttribute("datacount");
		System.out.println(so);
		}
		
		
		

		PrintWriter out= response.getWriter();
		response.setContentType("text/html");

		out.print("<html><head><link rel='stylesheet' type='text/css' href='/css/style.css'> "
           		+ "<style>"
           		+ "table {\r\n"
           		+ "    width: 100%;\r\n"
           		+ "    border-collapse: collapse;\r\n"
           		+ "    margin: 20px 0;\r\n"
           		+ "    font-size: 16px;\r\n"
           		+ "    font-family: Arial, sans-serif;\r\n"
           		+ "    text-align: left;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "table th, table td {\r\n"
           		+ "    padding: 12px 15px;\r\n"
           		+ "    border: 1px solid #ddd;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "table th {\r\n"
           		+ "    background-color: #4CAF50;\r\n"
           		+ "    color: white;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "table tr:nth-child(even) {\r\n"
           		+ "    background-color: #f9f9f9;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "table tr:nth-child(odd) {\r\n"
           		+ "    background-color: #ffffff;\r\n"
           		+ "}"
           		
           		+ "\r\n"
           		+ "table tr:hover {\r\n"
           		+ "    background-color: #f1f1f1;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "button {\r\n"
           		+ "    padding: 5px 10px;\r\n"
           		+ "    border: none;\r\n"
           		+ "    border-radius: 4px;\r\n"
           		+ "    color: white;\r\n"
           		+ "    cursor: pointer;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "button[style*='background-color:green'] {\r\n"
           		+ "    background-color: #28a745;\r\n"
           		+ "}\r\n"
           		+ "\r\n"
           		+ "button[style*='background-color:red'] {\r\n"
           		+ "    background-color: #dc3545;\r\n"
           		+ "}\r\n"
           		+ "</style>"
           		+ "</head><body><table border=1> "
           		+ "<tr><td>records from customers </td><td><form action='setadminattribute' method='post'><select name='op'><option>all</option><option >valid</option><option name='op'>not valid</option></select></td><td><button style='background-color:black' id='selectoption'>submit</button></form></td></tr>");
        
//        String query = "SELECT * FROM customers"; // Adjust your table name and columns
		String query=null;
		
		if (so.equals("all")) {
			
         query="SELECT * FROM customers ORDER BY acnumber ASC";
		}else if(so.equals("valid")) {
			
			 query="SELECT * FROM customers where kyc=1 ORDER BY acnumber ASC ";
			
		}else {
			 query="SELECT * FROM customers where kyc=0 ORDER BY acnumber ASC ";
		}
		
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234")) {
            // Create a Statement
            Statement stmt = conn.createStatement();

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery(query);
         
            out.print("<tr><td> customer id: </td>"
            		+ "<td> a/c number: </td>"
            		+ " <td> Name: </td>"
            		+ "<td> Address: </td>"
            		+ "<td> Email: </td>"
            		+ "<td> Phone: </td>"
            		+ "<td> Aadhaar: </td>"
            		+ "<td> valid: </td> "
            		+ "<td> not valid: </td></tr>");
            
            // Process the result
            while (rs.next()) {
            	String customerid=rs.getString("customerid");
            	long acnumber=rs.getLong("acnumber");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String adhaar = rs.getString("adhaar");
                int status = rs.getInt("kyc");
                out.print("<form action='adminaction' method='POST'>");
//                System.out.println(acnumber);
                out.print("<input type='hidden' name='acnumbervalue' value='" + acnumber + "' />");
                
                out.print("<tr> <td> "+customerid+" </td>"
                		+ "<td> "+acnumber+" </td>"+
                		"<td> "+name+" </td>"
                		+ "<td> "+address+" </td>"
                		+ "<td>"+email+"</td>"
                		+ "<td> "+phone+" </td>"
                		+ "<td> "+adhaar+" </td> ");
                if (status==0) {
                	out.print("<td></td><td><button style='background-color:red' name='"+acnumber+"'>turn on</td></tr> </form>");
                }else {
                	out.print("<td><button type='submit' style='background-color:green' name='"+acnumber+"'> turn off</td><td></td></tr> </form>");
                }
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        out.print("</table></body></html>");
		
		
		
		
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
