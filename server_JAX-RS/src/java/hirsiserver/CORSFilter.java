/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hirsiserver;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Jari
 */
@Provider
public class CORSFilter implements javax.servlet.Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException
    {
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Max-Age", "1209600");        

        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy()
    {
    }
}
