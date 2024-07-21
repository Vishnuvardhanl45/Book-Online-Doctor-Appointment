package com.example.hospital;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("generateOTP".equals(action)) {
            generateOTP(request, response);
        } else if ("validateOTP".equals(action)) {
            validateOTP(request, response);
        }
    }

    private void generateOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileNumber = request.getParameter("mobile_no");

        // Generate a random 6-digit OTP
        String otp = generateOTP();

        // Store OTP in session attribute for validation later
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);

        // Send OTP to the provided mobile number (mock implementation)
        boolean otpSent = sendOTP(mobileNumber, otp);

        if (otpSent) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private boolean sendOTP(String mobileNumber, String otp) {
        // Your code to send OTP using a third-party SMS gateway
        // Return true if OTP sent successfully, false otherwise
        // Example mock implementation:
        System.out.println("Sending OTP " + otp + " to " + mobileNumber);
        return true; // Assuming OTP sent successfully
    }

    private void validateOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enteredOTP = request.getParameter("otp");

        HttpSession session = request.getSession();
        String storedOTP = (String) session.getAttribute("otp");

        if (enteredOTP.equals(storedOTP)) {
            response.getWriter().write("OTP validated successfully!");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
