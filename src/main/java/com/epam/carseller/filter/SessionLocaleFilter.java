package com.epam.carseller.filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter")
public class SessionLocaleFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String DEFAULT_LANGUAGE = "ru";
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getParameter("sessionLocale") != null) {
            request.getSession().setAttribute("lang", request.getParameter("sessionLocale"));
        } else if (request.getSession().getAttribute("lang") != null) {
            System.out.println("if lang not empty: " + request.getSession().getAttribute("lang"));
        } else {
            request.getSession().setAttribute("lang", DEFAULT_LANGUAGE);
        }

        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

}
