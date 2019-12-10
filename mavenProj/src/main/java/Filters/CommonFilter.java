package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;


@WebFilter("/*")
public class CommonFilter implements Filter {
    ServletContext context;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;


        Enumeration<String> params = req.getParameterNames();

        this.context.log("\n" + "MY LOGS(" + req.getServletPath() + ")\nMETHOD::" + req.getMethod() + "\nURL::http://localhost:8080" + req.getRequestURI() + "\n" + "PARAMS::\n");

        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = servletRequest.getParameter(name);
            this.context.log("Request Params::{" + name + "=" + value + "}");
        }




        this.context.log("\nSIZE::" + httpResponse.getBufferSize() + "\nSTATUS::" + httpResponse.getStatus());


        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

    }
}
