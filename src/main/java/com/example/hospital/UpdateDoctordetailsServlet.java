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

public class UpdateDoctordetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieving form data
        String doctorID = request.getParameter("doctor-id");
        String doctorName = request.getParameter("doctor-name");
        String phoneNumber = request.getParameter("phone-number");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String specialization = request.getParameter("specialization");

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/hospital";
        String dbUser = "root";
        String dbPassword = "satya123";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        boolean updateSuccessful = false;

        try {
            // Establishing a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL query to update doctor details
            String sql = "UPDATE doctors SET name=?, specialization=?, email=?, phone=?, username=?, password=? WHERE id=?";

            preparedStatement = connection.prepareStatement(sql);

            // Setting parameters for the query
            preparedStatement.setString(1, doctorName);
            preparedStatement.setString(2, specialization);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, doctorID);

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                updateSuccessful = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirecting based on the update status
        if (updateSuccessful) {
            response.sendRedirect("UpdateDoctordetailsSuccess.html");
        } else {
            response.sendRedirect("UpdateDoctordetailsFailed.html");
        }
    }
}
