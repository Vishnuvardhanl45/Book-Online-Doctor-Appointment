package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewMedicinesServlet")
public class ViewMedicinesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/hospital";
        String user = "root";
        String password = "satya123";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medicine> medicines = new ArrayList<>();

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL statement
            String query = "SELECT * FROM medicine";
            stmt = conn.prepareStatement(query);

            // Execute query
            rs = stmt.executeQuery();

            // Process result set
            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(rs.getInt("id"));
                medicine.setName(rs.getString("name"));
                medicine.setPrice(rs.getDouble("price"));
                medicine.setQuantity(rs.getInt("quantity"));
                medicines.add(medicine);
            }

            // Set medicines as a request attribute
            request.setAttribute("medicines", medicines);

            // Forward to JSP to display medicines
            request.getRequestDispatcher("/viewMedicines.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., show error page)
            response.sendRedirect("error.jsp");
        } finally {
            // Close JDBC objects
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}