<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Get Total Price</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-6 col-sm-10">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title text-center">Total Price: ${sessionScope.totalPrice} rs</h5>
                    <form action="AddDeliveryAddressServlet" method="post" class="mt-3">
                        <input type="hidden" name="totalPrice" value="${sessionScope.totalPrice}" class="border-0 shadow-none">
                        <button type="submit" class="btn btn-primary w-100 border-0 shadow-none">Proceed to Delivery Details</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
