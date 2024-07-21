package com.example.hospital;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SendTotalPriceServlet")
public class SendTotalPriceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session
        if (session != null) {
            Integer totalPrice = (Integer) session.getAttribute("totalPrice");
            if (totalPrice != null) {
                request.setAttribute("totalPrice", totalPrice.toString()); // Convert Integer to String if needed
                request.getRequestDispatcher("/viewdeliveryaddress.jsp").forward(request, response);
            }
        }
    }
}
