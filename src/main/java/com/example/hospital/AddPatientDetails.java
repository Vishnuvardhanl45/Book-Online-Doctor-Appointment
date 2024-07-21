package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPatientDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String ageString = request.getParameter("age");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String disease = request.getParameter("disease");

        if (ageString == null || ageString.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in the age field.");
            request.getRequestDispatcher("AddPatientServlet.html").forward(request, response);
            return;
        }

        int age = Integer.parseInt(ageString);

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO patients (name, age, gender, phone, disease) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, disease);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                request.setAttribute("successMessage", "Patient details have been added successfully.");
                response.sendRedirect("AddPatientDetailsSuccess.html");
            } else {
                request.setAttribute("errorMessage", "Failed to add patient details.");
                request.getRequestDispatcher("AddPatientServlet.html").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
