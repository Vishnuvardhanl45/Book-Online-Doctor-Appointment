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

public class ViewPatientDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        List<PatientRecord> patients = new ArrayList<>();

        try {
            // Connect to the database
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients WHERE name=? AND phone=?");
            stmt.setString(1, name);
            stmt.setString(2, phone);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PatientRecord patient = new PatientRecord();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setPhone(rs.getString("phone"));
                patient.setDisease(rs.getString("disease"));
                patients.add(patient);
            }

            // Forward the request to the JSP page with patient details
            request.setAttribute("patients", patients);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewPatientDetails.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving patient details");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}