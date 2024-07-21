<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Redirecting to Razorpay</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    String zip = request.getParameter("zip");
    String amountToPay = request.getParameter("amount");
%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Redirecting to Razorpay</h5>
                    <p class="card-text">Please wait while we redirect you to the Razorpay gateway to complete the payment.</p>
                    <p><strong>Name:</strong> <%= name %></p>
                    <p><strong>Address:</strong> <%= address %></p>
                    <p><strong>City:</strong> <%= city %></p>
                    <p><strong>State:</strong> <%= state %></p>
                    <p><strong>Zip Code:</strong> <%= zip %></p>
                </div>
                <div class="card-footer text-muted">
                    Amount to Pay: <span id="amountToPay">${totalPrice} rs</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout(function() {
        var amount = document.getElementById("amountToPay").textContent;
        window.location.href = "razorpayGateway.html?amount=" + amount + "&name=<%= name %>&address=<%= address %>&city=<%= city %>&state=<%= state %>&zip=<%= zip %>";
    }, 3000);
</script>
</body>
</html>
