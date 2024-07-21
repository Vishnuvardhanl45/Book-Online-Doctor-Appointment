package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDoctorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM doctors";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                List<DoctorRecord> doctors = new ArrayList<>();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String specialization = resultSet.getString("specialization");
                    String phone = resultSet.getString("phone");

                    DoctorRecord doctor = new DoctorRecord(id, name, specialization, phone);
                    doctors.add(doctor);
                }

                // Set the list of doctors as a request attribute
                request.setAttribute("doctors", doctors);

                // Forward the request to the JSP page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/viewDoctors.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
