package com.example.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDoctorDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Retrieve form data
        String doctorName = request.getParameter("doctor-name");
        String phoneNumber = request.getParameter("phone-number");
        String emailId = request.getParameter("email-id");
        String specialization = request.getParameter("specialization");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Insert into database
        boolean insertSuccess = insertIntoDatabase(doctorName, phoneNumber, emailId, specialization, username,
                password);

        // Generate response
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body style=\"display: flex; justify-content: center; align-items: center; height: 100vh;\">");
        out.println("<h2>");
        if (insertSuccess) {
            out.println("Doctor details added successfully.");
            // Redirect to AddDoctorDetailsSuccess.html
            response.sendRedirect("AddDoctorDetailsSuccess.html");
            return; // Stop further execution to prevent printing "Failed to add doctor details"
        } else {
            out.println("Failed to add doctor details. Please try again.");
        }
        out.println("</h2>");
        out.println("</body>");
        out.println("</html>");
    }

    private boolean insertIntoDatabase(String doctorName, String phoneNumber, String emailId, String specialization,
            String username, String password) {
        try {
            // JDBC URL, username, and password
            String url = "jdbc:mysql://localhost:3306/hospital";
            String dbUsername = "root";
            String dbPassword = "satya123";

            // Create connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Prepare statement
            String sql = "INSERT INTO doctors (name, phone, email, specialization, username, password) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctorName);
            statement.setString(2, phoneNumber);
            statement.setString(3, emailId);
            statement.setString(4, specialization);
            statement.setString(5, username);
            statement.setString(6, password);

            // Execute statement
            int rowsInserted = statement.executeUpdate();

            // Close resources
            statement.close();
            connection.close();

            // Return true if insertion successful
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
