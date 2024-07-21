package com.example.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletViaPhoneno extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get phone number and password from the form
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/hospital";
        String dbUsername = "root";
        String dbPassword = "satya123";

        // Initialize database connection and prepared statement
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String query = "SELECT * FROM register WHERE phone=? AND password=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, phone);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        // User credentials are valid, redirect to index.html
                        response.sendRedirect("index.html");
                    } else {
                        // User credentials are invalid, display error message
                        PrintWriter out = response.getWriter();
                        out.println("<script>alert('Invalid phone number or password');</script>");
                        out.println("<script>window.location.href='Login.html';</script>");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection error
            response.sendRedirect("error.html");
        }
    }
}
