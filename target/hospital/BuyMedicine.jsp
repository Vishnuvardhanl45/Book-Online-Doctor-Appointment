<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buy Medicine</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="mt-5 text-center">Buy Medicine</h1>
            <form action="BuyMedicineServlet" method="post">
                <div class="form-group">
                    <label for="medicineName">Medicine Name:</label>
                    <input type="text" class="form-control" id="medicineName" name="medicineName" required>
                </div>

                <div class="form-group">
                    <label for="quantity">Quantity (Strips):</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Buy Now</button>
            </form>

            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) { 
            %>
                <div class="alert alert-danger mt-3"><%= errorMessage %></div>
            <% 
                } 
            %>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
