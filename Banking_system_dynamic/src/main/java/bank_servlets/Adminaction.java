package bank_servlets;

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
 * Servlet implementation class Adminaction
 */
@WebServlet("/adminaction")
public class Adminaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("i mhere again");
		Long ac= Long.parseLong(request.getParameter("acnumbervalue"));
		PrintWriter out = response.getWriter();
//		out.print(ac);
		
		
		
		
		  
        String query = "SELECT * FROM customers where acnumber="+ac; // Adjust your table name and columns

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234")) {
            // Create a Statement
            Statement stmt = conn.createStatement();

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery(query);

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
            		+ "    background-color: red;\r\n"
            		+ "    color: white;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "table tr:nth-child(even) {\r\n"
            		+ "    background-color: #f9f9f9;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "table tr:nth-child(odd) {\r\n"
            		+ "    background-color: #ffffff;\r\n"
            		+ "}\r\n"
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
            		+ "</head><body><table border=1> ");
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
                int pin=rs.getInt("pin");
                String color="";
                
                
                if (status==0) {
                	color="style='background-color:red'";
                }else {
                	color="style='background-color:green'";
                }
             
                out.print("<form action='Admin_change_details_status' method='post'>");
                out.print("<tr><th "+color+">custmer id</th><td> "+customerid+" </td> </tr>");
                out.print("<tr><th "+color+">a/c</th><td> "+acnumber+" </td> </tr>"); 
                out.print("<tr><th  "+color+">name</th><td> "+name+" </td> </tr>");
                out.print("<tr><th "+color+">adress</th><td> "+address+" </td> </tr>");
                out.print("<tr><th "+color+">email</th><td> "+email+" </td> </tr>"); 
                out.print("<tr><th "+color+">phone</th><td> "+phone+" </td> </tr>");
                out.print("<tr><th "+color+">adhaar</th><td> "+adhaar+" </td> </tr>");
                out.print("<tr><th "+color+" >pin</th><td> "+pin+" </td> </tr>");
                out.print("<tr><th "+color+" >status</th><td> "+status+" </td> </tr>");
                out.print("<input type='hidden' name='acnumbervalue' value='" + acnumber + "' />");
                
               
                if (status==0) {
                	out.print("<td> <input type='text' name='adminpin' /></td><td><button "+color+" name='"+acnumber+"'>turn on</td></tr> </form>");
                }else {
                	out.print("<td><input type='text' name='adminpin' /></td><td><button type='submit' "+color+" name='"+acnumber+"'> turn off</td></tr> </form>");
                }
         
            }
            out.print("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("i mhere");
		doGet(request, response);
	}

}
