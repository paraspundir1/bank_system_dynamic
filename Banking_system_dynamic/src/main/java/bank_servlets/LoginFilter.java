package bank_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		
		String realdata=request.getParameter("realcaptcha");
		String filleddata=request.getParameter("captchafilled");
		System.out.println("real captcha:"+realdata);
		System.out.println("filled data :"+filleddata);
		
		
		if (realdata.equals(filleddata)) {
//			System.out.println("hhjhk");
			
		 PrintWriter out= response.getWriter();
		 response.setContentType("text/html");
//		 String email=request.getParameter("email");
//		 
//		 
//		 
//		 out.print("<h1> enter otp sent on email id : "+email+"<form action='login' method='post'> <input type='hidden' value="+otp+"><input type='text' name='password'><button type='submit'>open admin pannel</button></form> ");
//			
		 fc.doFilter(request, response);
		}else {
			
		RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
		PrintWriter out= response.getWriter();
		out.print("wrong captcha fill it correcltly");
		response.setContentType("text/html");
		rd.include(request, response);
		}
		
		
		
		
	}

	

}
