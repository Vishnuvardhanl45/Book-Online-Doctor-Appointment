package com.example.hospital;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String ageString = request.getParameter("age");
        String gender = request.getParameter("gender");
        String disease = request.getParameter("disease");
        String phone = request.getParameter("phone");

        if (ageString == null || ageString.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in the age field.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("AddPatientServlet.html");
            dispatcher.forward(request, response);
            return;
        }

        int age = Integer.parseInt(ageString);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO patients (name, age, gender, disease,phone) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, disease);
            preparedStatement.setString(5, phone);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                request.setAttribute("successMessage", "Patient details have been added successfully.");
                response.sendRedirect("AddPatientSuccess.html");
            } else {
                request.setAttribute("errorMessage", "Failed to add patient details.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("AddPatientFailed.html");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
