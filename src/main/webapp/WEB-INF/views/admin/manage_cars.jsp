<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 2/27/2025
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
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
        <h2>Car Management</h2>

        <!-- Add Car Button -->
        <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addCarModal">
            Add New Car
        </button>

        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>Car_ID</th>
                <th>Registration Number</th>
                <th>Make</th>
                <th>Model</th>
                <th>Year</th>
                <th>Capacity</th>
                <th>Status</th>
                <th>Car Type</th>
                <th>Rate Per km</th>
                <th>Is Active</th>
                <th>Image</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Car car : carList) { %>
            <tr>
                <td><%= car.getCarId() %></td>
                <td><%= car.getRegistrationNumber() %></td>
                <td><%= car.getMake() %></td>
                <td><%= car.getModel() %></td>
                <td><%= car.getYear() %></td>
                <td><%= car.getCapacity() %></td>
                <td><%= car.getStatus() %></td>
                <td><%= car.getCarType() %></td>
                <td><%= car.getRatePerKm() %></td>
                <td><%= car.getIsActive() %></td>
                <td>
                    <% if (car.getImageUrl() != null && !car.getImageUrl().isEmpty()) { %>
                    <img src="<%= request.getContextPath() + car.getImageUrl() %>"
                         width="100" height="80" alt="Car Image">
                    <% } else { %>
                    <span>No Image</span>
                    <% } %>
                </td>
                <td>
                    <a href="/MegaCityCab_war_exploded/admin/edit_car?id=<%= car.getCarId() %>" class="btn btn-warning btn-sm">Edit</a>
                    <button class="btn btn-danger btn-sm delete-car" data-id="<%= car.getCarId() %>">Delete</button>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Add Car Modal -->
<div class="modal fade" id="addCarModal" tabindex="-1" role="dialog" aria-labelledby="addCarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addCarModalLabel">Add New Car</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="create">
                    <div class="form-group">
                        <label for="registrationNumber">Registration Number:</label>
                        <input type="text" class="form-control" id="registrationNumber" name="registrationNumber" required>
                    </div>
                    <div class="form-group">
                        <label for="make">Make:</label>
                        <input type="text" class="form-control" id="make" name="make" required>
                    </div>
                    <div class="form-group">
                        <label for="model">Model:</label>
                        <input type="text" class="form-control" id="model" name="model" required>
                    </div>
                    <div class="form-group">
                        <label for="year">Year:</label>
                        <input type="number" class="form-control" id="year" name="year" required>
                    </div>
                    <div class="form-group">
                        <label for="capacity">Capacity:</label>
                        <input type="number" class="form-control" id="capacity" name="capacity" required>
                    </div>
                    <div class="form-group">
                        <label for="status">Status:</label>
                        <select class="form-control" id="status" name="status" required>
                            <option value="Available">Available</option>
                            <option value="Unavailable">Unavailable</option>
                            <option value="In Service">In Service</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="carType">Car Type:</label>
                        <select class="form-control" id="carType" name="carType" required>
                            <option value="Mini">Mini</option>
                            <option value="Sedan">Sedan</option>
                            <option value="SUV">SUV</option>
                            <option value="Luxary Sedan">Luxury Sedan</option>
                            <option value="MPV">MPV</option>
                            <option value="VAN">VAN</option>
                            <option value="EV">EV</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ratePerKm">Rate Per Km (LKR):</label>
                        <input type="number" class="form-control" id="ratePerKm" name="ratePerKm" step="0.01" required>
                    </div>

                    <div class="form-group">
                        <label for="isActive">Active:</label>
                        <input type="checkbox" id="isActive" name="isActive" checked>
                    </div>
                    <div class="form-group">
                        <label for="image">Car Image:</label>
                        <input type="file" class="form-control-file" id="image" name="image" accept="image/*" required>
                    </div>
                    <button type="submit" class="btn btn-success">Add Car</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $(".delete-car").click(function () {
            var carId = $(this).data("id"); // Make sure it matches the HTML attribute

            if (!carId) {
                alert("Error: Car ID is missing!");
                return;
            }

            if (confirm("Are you sure you want to delete this car?")) {
                $.ajax({
                    url: "manage_cars?id=" + carId, // Ensure parameter name is correct
                    type: "DELETE",
                    success: function (response) {
                        if (response.trim() === "success") {
                            alert("Car deleted successfully.");
                            location.reload();
                        } else {
                            alert("Failed to delete car.");
                        }
                    },
                    error: function () {
                        alert("Error deleting car.");
                    }
                });
            }
        });
    });
</script>

</body>
</html>




