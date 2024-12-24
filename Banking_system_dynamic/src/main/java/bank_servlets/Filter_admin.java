package bank_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Filter_admin  implements Filter  {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		

		
		
		
		PrintWriter out = response.getWriter();
		String pass=request.getParameter("password");
		if (pass.equals("Paras123@")) {
		fc.doFilter(request, response);
	}
	else {
		RequestDispatcher rd= request.getRequestDispatcher("admin.html");
		out.println("wrong password");
		response.setContentType("text/html");
		rd.include(request, response);
		}
		}

}
