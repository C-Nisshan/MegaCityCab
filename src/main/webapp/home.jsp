<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/12/2025
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mega City Cab - Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .hero {
            background: url('https://source.unsplash.com/1600x900/?taxi,city') no-repeat center center;
            background-size: cover;
            height: 500px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            text-align: center;
        }
        .hero h1 {
            font-size: 50px;
            font-weight: bold;
            text-shadow: 2px 2px 5px rgba(0,0,0,0.5);
        }
        .btn-custom {
            background: #007BFF;
            border: none;
            padding: 12px 25px;
            font-size: 18px;
            transition: 0.3s;
            color: white;
            text-transform: uppercase;
        }
        .btn-custom:hover {
            background: #0056b3;
        }
        .section {
            padding: 50px 20px;
            text-align: center;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<!-- Hero Section -->
<div class="hero">
    <div>
        <h1>Fast & Reliable Cab Service in Colombo</h1>
        <p>Book your ride now with Mega City Cab</p>
        <a href="<%= request.getContextPath() %>/booking">Book a ride</a>
    </div>
</div>

<!-- Features Section -->
<div class="container">
    <div class="row section">
        <div class="col-md-4">
            <img src="https://source.unsplash.com/300x200/?car,city" class="img-fluid rounded">
            <h3>Easy Booking</h3>
            <p>Book a cab in just a few clicks.</p>
        </div>
        <div class="col-md-4">
            <img src="https://source.unsplash.com/300x200/?taxi,road" class="img-fluid rounded">
            <h3>Affordable Prices</h3>
            <p>Enjoy the best rates in the city.</p>
        </div>
        <div class="col-md-4">
            <img src="https://source.unsplash.com/300x200/?driver,car" class="img-fluid rounded">
            <h3>Professional Drivers</h3>
            <p>Well-trained, courteous drivers.</p>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>

