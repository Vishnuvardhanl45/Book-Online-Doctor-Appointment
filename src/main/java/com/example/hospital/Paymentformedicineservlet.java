package com.example.hospital;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.UUID;

public class Paymentformedicineservlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Parse request parameters
        String patientName = request.getParameter("patientName");
        String phoneNumber = request.getParameter("phoneNumber");

        // Generate appointment ID
        String appointmentId = UUID.randomUUID().toString();

        // Redirect to Razorpay payment page
        String razorpayPaymentLink = "https://rzp.io/i/VRiH7MAlS"; // Razorpay payment link
        response.sendRedirect(razorpayPaymentLink + "?patientName=" + patientName + "&phoneNumber=" + phoneNumber
                + "&appointmentId=" + appointmentId);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/index.html");
    }

}
