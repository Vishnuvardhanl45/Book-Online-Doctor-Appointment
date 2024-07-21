<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Patients</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 60vh;
            margin: 0;
            margin-top: 100px;
            background-color: #f4f4f4;
        }

       .table-container {
            width: 80%;
            max-width: 1200px;
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        @media (max-width: 600px) {
           .table-container {
                width: 100%;
                margin: 10px;
                padding: 10px;
            }
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10 col-lg-8 table-container">
                <h1>Patient List</h1>
                <table class="table table-bordered table-striped">
                    <thead class="thead-light">
                        <tr>
                            <th>Patient ID</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Gender</th>
                            <th>Phone no</th>
                            <th>Disease</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="patient" items="${patients}">
                            <tr>
                                <td>${patient.id}</td>
                                <td>${patient.name}</td>
                                <td>${patient.age}</td>
                                <td>${patient.gender}</td>
                                <td>${patient.phone}</td>
                                <td>${patient.disease}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>