<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/1/2025
  Time: 12:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.megacitycab.megacitycab.models.Car" %>
<%
    Car car = (Car) request.getAttribute("car");
%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Car" %>
<%
    List<Car> carList = (List<Car>) request.getAttribute("carList");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Car Management</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>
            <div class="content">
                <h2>Edit Car</h2>

                <form action="" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= car.getCarId() %>">

                    <label>Registration Number:</label>
                    <input type="text" name="registrationNumber" value="<%= car.getRegistrationNumber() %>" required>

                    <label>Make:</label>
                    <input type="text" name="make" value="<%= car.getMake() %>" required>

                    <label>Model:</label>
                    <input type="text" name="model" value="<%= car.getModel() %>" required>

                    <label>Year:</label>
                    <input type="number" name="year" value="<%= car.getYear() %>" required>

                    <label>Capacity:</label>
                    <input type="number" name="capacity" value="<%= car.getCapacity() %>" required>

                    <label>Status:</label>
                    <select name="status" required>
                        <option value="Available" <%= "Available".equals(car.getStatus()) ? "selected" : "" %>>Available</option>
                        <option value="Unavailable" <%= "Unavailable".equals(car.getStatus()) ? "selected" : "" %>>Unavailable</option>
                        <option value="In Service" <%= "In Service".equals(car.getStatus()) ? "selected" : "" %>>In Service</option>
                    </select>

                    <div class="form-group">
                        <label for="carType">Car Type:</label>
                        <select class="form-control" id="carType" name="carType" required>
                            <option value="Mini" <%= "Mini".equals(car.getCarType()) ? "selected" : ""%>>Mini</option>
                            <option value="Sedan" <%= "Sedan".equals(car.getCarType()) ? "selected" : ""%>>Sedan</option>
                            <option value="SUV" <%= "SUV".equals(car.getCarType()) ? "selected" : ""%>>SUV</option>
                            <option value="Luxary Sedan" <%= "Luxury Sedan".equals(car.getCarType()) ? "selected" : ""%>>Luxury Sedan</option>
                            <option value="MPV" <%= "MPV".equals(car.getCarType()) ? "selected" : ""%>>MPV</option>
                            <option value="VAN" <%= "VAN".equals(car.getCarType()) ? "selected" : ""%>>VAN</option>
                            <option value="EV" <%= "EV".equals(car.getCarType()) ? "selected" : ""%>>EV</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ratePerKm">Rate Per Km (LKR):</label>
                        <input type="number" class="form-control" id="ratePerKm" name="ratePerKm" step="0.01" value="<%= car.getRatePerKm() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="isActive">Active:</label>
                        <input type="hidden" name="isActive" value="false">
                        <input type="checkbox" id="isActive" name="isActive" value="true" <%= car.getIsActive() ? "checked" : "" %>>
                    </div>

                    <label>Current Image:</label>
                    <% if (car.getImageUrl() != null) { %>
                    <img src="<%= request.getContextPath() + car.getImageUrl() %>" width="100">
                    <input type="hidden" name="existingImage" value="<%= car.getImageUrl() %>">
                    <% } %>

                    <label>New Image (optional):</label>
                    <input type="file" class="form-control-file" id="image" name="image" accept="image/*">

                    <button type="submit">Update Car</button>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS & jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>


