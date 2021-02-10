package edu.epam.bookie.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class CrossScriptingFilter implements Filter {
    private static final String EMPTY = "";
    private static final String SCRIPT_REGEX = "</?script>";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Enumeration<String> enumeration = servletRequest.getAttributeNames();
        while (enumeration.hasMoreElements()){
            String parameterName = enumeration.nextElement();
            String parameter = servletRequest.getParameter(parameterName);
            if(parameter != null){
                String newParameter = parameter.replaceAll(SCRIPT_REGEX, EMPTY);
                servletRequest.setAttribute(parameterName,newParameter);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
