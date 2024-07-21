package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewPatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Connect to the database
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients");

            // Create a list to hold patient data
            List<PatientRecord> patients = new ArrayList<>();

            // Retrieve patient data from the result set and add to the list
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

            // Set the patient list as a request attribute
            request.setAttribute("patients", patients);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewPatients.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
