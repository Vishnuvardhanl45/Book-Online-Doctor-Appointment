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
import javax.servlet.http.HttpSession;

public class AdminLoginServletViaEmail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String jdbcUrl = "jdbc:mysql://localhost:3306/hospital";
        String dbUsername = "root";
        String dbPassword = "satya123";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String query = "SELECT * FROM doctors WHERE email=? AND password=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("isAdmin", true);

                        response.sendRedirect("AdminDashboard.html");
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<script>alert('Invalid email id or password');</script>");
                        out.println("<script>window.location.href='AdminLoginViaEmail.html';</script>");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
