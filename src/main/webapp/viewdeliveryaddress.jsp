<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Delivery Address</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
        }
        .container {
            background: white;
            padding: 1.5rem;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            padding: 50px;
        }
        h1 {
            margin-bottom: 1rem;
            font-size: 1.5rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-3">YourDelivery Address</h1>
        <p>Full Name: ${param.name}</p>
        <p>Address: ${param.address}</p>
        <p>City: ${param.city}</p>
        <p>State: ${param.state}</p>
        <p>Zip Code: ${param.zip}</p>
        <p>Total Price: ${totalPrice} rs</p>
        
        <form action="payment.jsp" method="post">
            <input type="hidden" name="name" value="${param.name}">
            <input type="hidden" name="address" value="${param.address}">
            <input type="hidden" name="city" value="${param.city}">
            <input type="hidden" name="state" value="${param.state}">
            <input type="hidden" name="zip" value="${param.zip}">
            <input type="hidden" name="totalPrice" value="${totalPrice}">
            <button type="submit" class="btn btn-primary">Proceed to Payment</button>
        </form>
    </div>
</body>
</html>
