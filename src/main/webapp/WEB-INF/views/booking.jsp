<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/13/2025
  Time: 7:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Car" %>
<%
    List<Car> carList = (List<Car>) request.getAttribute("carList");
%>
<html>
<head>
    <title>Booking</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .main-container {
            display: flex;
        }
        .card {
            border-radius: 15px;
            overflow: hidden;
            transition: transform 0.3s ease-in-out;
        }
        .card:hover {
            transform: scale(1.05);
        }
        .btn-success {
            border-radius: 10px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="main-container container mt-4">
    <div class="row w-100">
        <% if(carList != null && !carList.isEmpty()) { %>
        <% for (Car car : carList) { %>
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm">
                <% if (car.getImageUrl() != null && !car.getImageUrl().isEmpty()) { %>
                <img src="<%= request.getContextPath() + car.getImageUrl() %>" class="card-img-top" alt="Car Image" style="height: 200px; object-fit: cover;">
                <% } else { %>
                <div class="text-center bg-light" style="height: 200px; display: flex; align-items: center; justify-content: center;">
                    <span>No Image</span>
                </div>
                <% } %>
                <div class="card-body">
                    <h5 class="card-title"><%= car.getMake() %> <%= car.getModel() %></h5>
                    <p class="card-text">
                        <strong>Registration No:</strong> <%= car.getRegistrationNumber() %><br>
                        <strong>Capacity:</strong> <%= car.getCapacity() %> persons<br>
                        <strong>Type:</strong> <%= car.getCarType() %><br>
                        <strong>Rate:</strong> Rs<%= car.getRatePerKm() %> per km
                    </p>
                    <a href="/MegaCityCab_war_exploded/customer/book_car?id=<%= car.getCarId() %>" class="btn btn-success btn-block">Book Now</a>
                </div>
            </div>
        </div>
        <% } %>
        <% } else { %>
        <div class="col-12 text-center">
            <p class="alert alert-warning">No cars available</p>
        </div>
        <% } %>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
