package com.example.hospital;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddDeliveryAddressServlet")
public class AddDeliveryAddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/AddDeliveryAddress.jsp").forward(request, response);
    }
}
