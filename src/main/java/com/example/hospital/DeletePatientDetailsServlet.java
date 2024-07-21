package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePatientDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data (patient ID or mobile number)
        String patientId = request.getParameter("patient-id");

        // Delete from database
        boolean deleteSuccess = deleteFromDatabase(patientId);

        // Redirect to success page if deletion successful
        if (deleteSuccess) {
            response.sendRedirect("DeletePatientDetailsSuccess.html");
        } else {
            // Handle deletion failure
            response.sendRedirect("DeletePatientDetailsError.html");
        }
    }

    // Method to delete data from database
    private boolean deleteFromDatabase(String patientId) {
        try {
            // JDBC URL, username, and password
            String url = "jdbc:mysql://localhost:3306/hospital";
            String username = "root";
            String password = "satya123";

            // Create connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare statement
            String sql = "DELETE FROM patients WHERE id = ? OR phone = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patientId);
            statement.setString(2, patientId);

            // Execute statement
            int rowsDeleted = statement.executeUpdate();

            // Close resources
            statement.close();
            connection.close();

            // Return true if deletion successful
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
