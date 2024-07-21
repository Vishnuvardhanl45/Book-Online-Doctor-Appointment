package com.example.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateMedicineServlet")
public class UpdateMedicineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String medicineName = request.getParameter("medicineName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "satya123");

            // Prepare the SQL statement
            String sql = "UPDATE medicine SET quantity = ?, price = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setDouble(2, price);
            statement.setString(3, medicineName);

            // Execute the statement
            statement.executeUpdate();

            // Close the connection
            conn.close();

            // Redirect to a success page
            response.sendRedirect("AdminDashboard.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
