<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Delivery Address</title>
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
       .form-group label {
            font-size: 0.9rem;
        }
       .form-control {
            font-size: 0.9rem;
        }
       .btn {
            margin-top: 1rem;
            width: 100%;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-3">Add Delivery Address</h1>
        <form action="SendTotalPriceServlet" method="post">
            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea class="form-control" id="address" name="address" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" id="city" name="city" required>
            </div>
            <div class="form-group">
                <label for="state">State</label>
                <input type="text" class="form-control" id="state" name="state" required>
            </div>
            <div class="form-group">
                <label for="zip">Zip Code</label>
                <input type="text" class="form-control" id="zip" name="zip" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>