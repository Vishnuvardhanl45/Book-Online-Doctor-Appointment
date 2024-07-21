package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String patientIdString = request.getParameter("patientid");
        String doctorIdString = request.getParameter("doctorid");
        String appointmentDate = request.getParameter("appointmentdate");
        String timeSlot = request.getParameter("timeslot");

        if (patientIdString == null || doctorIdString == null || appointmentDate == null) {
            // Handle missing parameters
            response.sendRedirect(request.getContextPath() + "/MissingParameters.html");
            return;
        }

        try {
            int patientid = Integer.parseInt(patientIdString);
            int doctorid = Integer.parseInt(doctorIdString);

            try (Connection connection = DBConnection.getConnection()) {
                Patient patient = new Patient(connection);
                Doctor doctor = new Doctor(connection);

                if (patient.getPatientById(patientid) && doctor.getDoctorById(doctorid)) {
                    if (checkDoctorAvailability(doctorid, appointmentDate, connection)) {
                        // Doctor is available, forward to payment section
                        request.setAttribute("patientid", patientid);
                        request.setAttribute("doctorid", doctorid);
                        request.setAttribute("appointmentdate", appointmentDate);
                        request.setAttribute("timeslot", timeSlot);
                        request.getRequestDispatcher("/payment.html").forward(request, response);
                    } else {
                        // Doctor is not available on the selected date
                        response.sendRedirect(request.getContextPath() + "/DoctorNotAvailable.html");
                    }
                } else {
                    // Patient or Doctor not found
                    response.sendRedirect(request.getContextPath() + "/InvalidPatientOrDoctor.html");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database error
                response.sendRedirect(request.getContextPath() + "/DatabaseError.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid number format
            response.sendRedirect(request.getContextPath() + "/InvalidNumberFormat.jsp");
        }
    }

    private boolean checkDoctorAvailability(int doctorid, String appointmentdate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorid);
            preparedStatement.setString(2, appointmentdate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
