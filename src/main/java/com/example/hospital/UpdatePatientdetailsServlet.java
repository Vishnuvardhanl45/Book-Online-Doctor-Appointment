package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePatientdetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieving form data
        String patientID = request.getParameter("patient-id");
        String patientName = request.getParameter("patient-name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phone-number");
        String disease = request.getParameter("disease");

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

            // Check if patient ID exists in the database
            String checkSql = "SELECT * FROM patients WHERE id=?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setString(1, patientID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // SQL query to update patient details
                String sql = "UPDATE patients SET name=?, age=?, gender=?, phone=?, disease=? WHERE id=?";

                preparedStatement = connection.prepareStatement(sql);

                // Setting parameters for the query
                preparedStatement.setString(1, patientName);
                preparedStatement.setInt(2, Integer.parseInt(age));
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, phoneNumber);
                preparedStatement.setString(5, disease);
                preparedStatement.setString(6, patientID);

                // Executing the query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    updateSuccessful = true;
                }
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
            response.sendRedirect("UpdatePatientdetailsSuccess.html");
        } else {
            response.sendRedirect("UpdatePatientdetailsFailed.html");
        }
    }
}
