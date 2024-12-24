package bank_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Filter_validation implements Filter  {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		
		String  name=request.getParameter("name");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
//		Integer adhaar= Integer.getInteger(request.getParameter("adhaar"))  ;
		
		String password=request.getParameter("pass");
		String tick=request.getParameter("agree-term");

		String adhaarStr = request.getParameter("adhaar");
		
		
		
//		
//		System.out.println(name);
//		System.out.println(address);
//		System.out.println(email);
//		System.out.println(phone);
//		System.out.println(adhaar);
//		System.out.println(password);
//		System.out.println(tick);
//		
		Long adhaar=0l;
		

        // Initialize validation flag and error message
        boolean valid = true;
        String errorMessage = "";

        // Validate name (only English letters)
        if (name == null || name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            valid = false;
            errorMessage += "Name must only contain English letters.\n";
        }

        // Validate email (must end with @gmail.com)
        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            valid = false;
            errorMessage += "Email must be a valid Gmail address.\n";
        }

        // Validate phone (must be 10 digits, only numbers)
        if (phone == null || phone.isEmpty() || !phone.matches("\\d{10}")) {
            valid = false;
            errorMessage += "Phone number must be 10 digits and only contain numbers.\n";
        }

        // Validate Aadhaar (must be 12 digits, only numbers)
//        if (adhaar1 == null || adhaar1.isEmpty() || !adhaar1.matches("\\d{12}")) {
//            valid = false;
//            errorMessage += "Aadhaar number must be exactly 12 digits and only contain numbers.\n";
//        }
        if (adhaarStr == null || adhaarStr.isEmpty() || !adhaarStr.matches("\\d{12}")) {
            valid = false;
            errorMessage += "Aadhaar number must be exactly 12 digits and only contain numbers.\n";
        } else {
             adhaar = Long.parseLong (adhaarStr);
            // Now you can use the integer value of Aadhaar if needed
        }

        // Validate password (at least 8 characters, alphanumeric)
        if (password == null || password.isEmpty() || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            valid = false;
            errorMessage += "Password must be at least 8 characters long and contain both letters and numbers.\n";
        }
        if (logicforvalidation.CheckDataandReturn.isAdhaarExist(adhaar)) {
            valid = false;
            errorMessage += "adhaar already existing.\n";
        }

        // Validate if terms are agreed
        if (tick == null || !tick.equals("on")) {
            valid = false;
            errorMessage += "You must agree to the terms and conditions.\n";
        }

        // If validation fails, forward to error page with the error message
        if (!valid) {
        	
            request.setAttribute("errorMessage", errorMessage);
            
            
            
       	 response.setContentType("text/html");

	        // Get the PrintWriter to write response
	        java.io.PrintWriter out = response.getWriter();

	        // Example message to display in the alert
	        String message = "This is a JavaScript alert from a Servlet!";

	        // Write the HTML and JavaScript
	        
	        out.println("<html>");
	        out.println("<head><title>Alert Example</title></head>");
	        out.println("<body>");
	        out.println("<script>");
	        
	        // Escape the message to prevent any JavaScript injection issues
	       
	        out.println("alert('please fill data correctly');");
	        out.println("</script>");
	        out.println("<h1>Check the alert box!</h1>");
	        out.println("</body>");
	        out.println("</html>");
            out.println(errorMessage);
            response.setContentType("text/html");
            request.getRequestDispatcher("registration.html").include(request, response);
            return;
        }

        // If validation passes, continue with the filter chain
        
		fc.doFilter(request, response);
		
	}

}
