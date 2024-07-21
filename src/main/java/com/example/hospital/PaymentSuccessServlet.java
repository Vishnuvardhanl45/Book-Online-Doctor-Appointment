package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentSuccessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int patientId = (int) session.getAttribute("patientid");
        int doctorId = (int) session.getAttribute("doctorid");
        String appointmentDate = (String) session.getAttribute("appointmentdate");
        String timeSlot = (String) session.getAttribute("timeslot");

        try (Connection connection = DBConnection.getConnection()) {
            if (addAppointment(patientId, doctorId, appointmentDate, timeSlot, connection)) {
                // Appointment added successfully
                // You can redirect to a success page or do other necessary actions
                response.sendRedirect(request.getContextPath() + "/AppointmentBookedSuccess.html");
            } else {
                // Failed to add appointment
                response.sendRedirect(request.getContextPath() + "/BookAppointmentFailed.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.sendRedirect(request.getContextPath() + "/DatabaseError.jsp");
        }
    }

    private boolean addAppointment(int patientId, int doctorId, String appointmentDate, String timeSlot,
            Connection connection) {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, timeslot) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            preparedStatement.setString(3, appointmentDate);
            preparedStatement.setString(4, timeSlot);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
