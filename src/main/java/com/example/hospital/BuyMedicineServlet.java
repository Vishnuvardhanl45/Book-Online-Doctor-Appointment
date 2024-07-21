package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BuyMedicineServlet")
public class BuyMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); // Start a new session or use an existing one
        String medicineName = request.getParameter("medicineName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String dbUrl = "jdbc:mysql://localhost:3306/hospital";
        String dbUser = "root";
        String dbPassword = "satya123";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String query = "SELECT price FROM medicine WHERE name =?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, medicineName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int price = rs.getInt("price");
                int totalPrice = price * quantity;

                session.setAttribute("totalPrice", totalPrice); // Set totalPrice as a session attribute
                response.sendRedirect("GetTotalPrice.jsp"); // Redirect to GetTotalPrice.jsp
            } else {
                throw new ServletException("Medicine not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
