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
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Regsitration
 */
@WebServlet("/register")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();		
		String  name=request.getParameter("name");
	String address=request.getParameter("address");
	String email=request.getParameter("email");
	String phone=request.getParameter("phone");
	Long adhaar=Long.parseLong( request.getParameter("adhaar"));
	String password=request.getParameter("pass");

	System.out.println("ishjdshj");

	 try { Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234");
      
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO customers (name, address, email, phone, adhaar, password,pin,kyc) VALUES (?, ?, ?, ?, ?, ?, ?,?)");

        pstmt.setString(1, name);
        pstmt.setString(2, address);
        pstmt.setString(3, email);
        pstmt.setString(4, phone);
        pstmt.setLong(5, adhaar);
        pstmt.setString(6, password);
        pstmt.setInt(7, 1234);
        pstmt.setInt(8, 0);
       

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
           out.println("Account created succesfully !");
           
          
           
           
           
           response.setContentType("text/html");
           RequestDispatcher rd= request.getRequestDispatcher("login.html");
           rd.include(request, response);
           
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
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
