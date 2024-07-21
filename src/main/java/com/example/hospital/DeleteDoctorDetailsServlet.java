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

public class DeleteDoctorDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Retrieve form data (doctor ID or mobile number)
        String doctorId = request.getParameter("doctor-id");

        // Delete from database
        boolean deleteSuccess = deleteFromDatabase(doctorId);

        // Redirect to success page if deletion successful
        if (deleteSuccess) {
            response.sendRedirect("DeleteDoctorDetailsSuccess.html");
        } else {
            response.sendRedirect("DeleteDoctorDetailsError.html");
        }
    }

    // Method to delete data from database
    private boolean deleteFromDatabase(String doctorIdOrPhone) {
        try {
            // JDBC URL, username, and password
            String url = "jdbc:mysql://localhost:3306/hospital";
            String username = "root";
            String password = "satya123";

            // Create connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare statement
            String sql = "DELETE FROM doctors WHERE id = ? OR phone = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctorIdOrPhone);
            statement.setString(2, doctorIdOrPhone);

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
