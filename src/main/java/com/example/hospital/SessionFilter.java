package com.example.hospital;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Define URLs for user login, registration, and logout
        String loginURI = req.getContextPath() + "/Login.html";
        String logoutURI = req.getContextPath() + "/LogoutServlet";

        // Define protected paths that require user login
        String[] protectedUserPaths = {
                "/index.html",
                "/AddPatientServlet.html",
                "/ViewPatientByDetails.html",
                "/viewDoctors.jsp",
                "/BookAppointmentServlet.html",
                "/UpdatePatientdetails.html",
                "/AddPatientServlet",
                "/ViewDoctorServlet"
        };

        // Check if user is logged in
        boolean isUserLoggedIn = session != null && session.getAttribute("username") != null;

        // Check if request is for user login or logout
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean logoutRequest = req.getRequestURI().equals(logoutURI);

        // Check if request is accessing a protected user path
        boolean accessingProtectedUserPath = false;
        for (String path : protectedUserPaths) {
            if (req.getRequestURI().contains(path)) {
                accessingProtectedUserPath = true;
                break;
            }
        }

        // If user is logged in or accessing login, logout, or non-protected
        // paths, allow access
        if (isUserLoggedIn || loginRequest || logoutRequest || !accessingProtectedUserPath) {
            chain.doFilter(request, response);
        } else {
            // Redirect to user login page if not logged in and trying to access a protected
            // user path
            res.sendRedirect(loginURI);
        }
    }

    public void destroy() {
        // Cleanup code if needed
    }
}