package org.example.spring1114.Filter;

import jakarta.servlet.*;

import java.io.IOException;

public class AFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AFilter start");
        chain.doFilter(request, response);
        System.out.println("AFilter end");
    }
}
