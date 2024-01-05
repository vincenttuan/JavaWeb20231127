package vending_machine.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(value = {"/vending/main", "/vending/submit"})
//@WebFilter(value = {"/vending/*"})
@WebFilter("/vending/*")
public class CheckLoginFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		res.getWriter().print("Stop here !");
	}
	
}