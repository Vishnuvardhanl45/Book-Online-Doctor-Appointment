package com.example.hospital;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AdminSessionFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        // Initialization code if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Define URLs for admin login, registration, and logout
        String loginURI = req.getContextPath() + "/AdminLogin.html";
        String logoutURI = req.getContextPath() + "/AdminLogoutServlet";

        // Define protected paths that require admin login
        String[] protectedAdminPaths = {
                "/AdminDashboard.html",
                "/AddDoctorDetails.html",
                "/DeleteDoctorDetails.html",
                "/UpdateDoctorDetails.html",
                "/AddPatientDetails.html",
                "/DeletePatientDetails.html",
                "/ViewPatientServlet"
        };

        // Check if admin is logged in
        boolean isAdminLoggedIn = session != null && session.getAttribute("isAdmin") != null;

        // Check if request is for admin login or logout
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean logoutRequest = req.getRequestURI().equals(logoutURI);

        // Check if request is accessing a protected admin path
        boolean accessingProtectedAdminPath = false;
        for (String path : protectedAdminPaths) {
            if (req.getRequestURI().contains(path)) {
                accessingProtectedAdminPath = true;
                break;
            }
        }

        // If admin is logged in or accessing login, logout, or non-protected
        // paths, allow access
        if (isAdminLoggedIn || loginRequest || logoutRequest || !accessingProtectedAdminPath) {
            chain.doFilter(request, response);
        } else {
            // Redirect to admin login page if not logged in and trying to access a
            // protected admin path
            res.sendRedirect(loginURI);
        }
    }

    public void destroy() {
        // Cleanup code if needed
    }
}