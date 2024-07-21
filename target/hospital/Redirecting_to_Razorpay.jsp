<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Redirecting to Razorpay</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Redirecting to Razorpay</h5>
                        <p class="card-text">Please wait while we redirect you to the Razorpay gateway to complete the payment.</p>
                        <p><strong>Name:</strong> <%= request.getParameter("name") %></p>
                        <p><strong>Address:</strong> <%= request.getParameter("address") %></p>
                        <p><strong>City:</strong> <%= request.getParameter("city") %></p>
                        <p><strong>State:</strong> <%= request.getParameter("state") %></p>
                        <p><strong>Zip Code:</strong> <%= request.getParameter("zip") %></p>
                    </div>
                    <div class="card-footer text-muted">
                        Amount to Pay: <span id="amountToPay"><%= request.getParameter("amount") %></span> Rs
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        setTimeout(function() {
            var amount = document.getElementById("amountToPay").textContent;
            window.location.href = "razorpayGateway.html?amount=" + amount;
        }, 3000); // Adjust delay time as needed
    </script>
</body>
</html>
