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
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .main-container {
            display: flex;
        }
        .content{
            padding: 20px;
        }
        .form-container {
            max-width: 900px;
            margin: 15px;
        }

        .class-row{
            display: flex;
            justify-content: center;
            align-items: center;
            margin-left: 30px;
        }
        .form-section {
            background-color: #f1f1f1;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .form-section h3 {
            margin-bottom: 15px;
            color: #007bff;
        }
        .img-preview {
            max-width: 150px; /* Set a max width */
            height: auto; /* Maintain aspect ratio */
            margin-top: 10px;
        }
        /* Added padding to the content of the columns */
        .form-column {
            padding: 15px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="main-container">
    <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>

    <div class="content">
        <h2>Edit Car</h2>

        <div class="form-container">
            <form action="" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= car.getCarId() %>">

                <div class="row class-row">
                    <!-- Car Details Column -->
                    <div class="col-md-4 form-column">
                        <div class="form-section">
                            <h3>Car Details</h3>
                            <div class="form-group">
                                <label>Registration Number:</label>
                                <input type="text" name="registrationNumber" class="form-control" value="<%= car.getRegistrationNumber() %>" required>
                            </div>

                            <div class="form-group">
                                <label>Make:</label>
                                <input type="text" name="make" class="form-control" value="<%= car.getMake() %>" required>
                            </div>

                            <div class="form-group">
                                <label>Model:</label>
                                <input type="text" name="model" class="form-control" value="<%= car.getModel() %>" required>
                            </div>

                            <div class="form-group">
                                <label>Year:</label>
                                <input type="number" name="year" class="form-control" value="<%= car.getYear() %>" required>
                            </div>

                            <div class="form-group">
                                <label>Capacity:</label>
                                <input type="number" name="capacity" class="form-control" value="<%= car.getCapacity() %>" required>
                            </div>
                        </div>
                    </div>

                    <!-- Car Status Column -->
                    <div class="col-md-4 form-column">
                        <div class="form-section">
                            <h3>Car Status</h3>
                            <div class="form-group">
                                <label>Status:</label>
                                <select name="status" class="form-control" required>
                                    <option value="Available" <%= "Available".equals(car.getStatus()) ? "selected" : "" %>>Available</option>
                                    <option value="Unavailable" <%= "Unavailable".equals(car.getStatus()) ? "selected" : "" %>>Unavailable</option>
                                    <option value="In Service" <%= "In Service".equals(car.getStatus()) ? "selected" : "" %>>In Service</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Car Type:</label>
                                <select name="carType" class="form-control" required>
                                    <option value="Mini" <%= "Mini".equals(car.getCarType()) ? "selected" : ""%>>Mini</option>
                                    <option value="Sedan" <%= "Sedan".equals(car.getCarType()) ? "selected" : ""%>>Sedan</option>
                                    <option value="SUV" <%= "SUV".equals(car.getCarType()) ? "selected" : ""%>>SUV</option>
                                    <option value="Luxury Sedan" <%= "Luxury Sedan".equals(car.getCarType()) ? "selected" : ""%>>Luxury Sedan</option>
                                    <option value="MPV" <%= "MPV".equals(car.getCarType()) ? "selected" : ""%>>MPV</option>
                                    <option value="VAN" <%= "VAN".equals(car.getCarType()) ? "selected" : ""%>>VAN</option>
                                    <option value="EV" <%= "EV".equals(car.getCarType()) ? "selected" : ""%>>EV</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Rate Per Km (LKR):</label>
                                <input type="number" name="ratePerKm" class="form-control" value="<%= car.getRatePerKm() %>" step="0.01" required>
                            </div>

                            <div class="form-group">
                                <label>Active:</label>
                                <input type="checkbox" name="isActive" value="true" <%= car.getIsActive() ? "checked" : "" %>> Yes
                            </div>
                        </div>
                    </div>

                    <!-- Image Section Column -->
                    <div class="col-md-4 form-column">
                        <div class="form-section">
                            <h3>Image</h3>
                            <div class="form-group">
                                <label>Current Image:</label>
                                <% if (car.getImageUrl() != null) { %>
                                <img src="<%= request.getContextPath() + car.getImageUrl() %>" class="img-preview">
                                <input type="hidden" name="existingImage" value="<%= car.getImageUrl() %>">
                                <% } %>
                            </div>
                            <div class="form-group">
                                <label>New Image (optional):</label>
                                <input type="file" class="form-control-file" name="image" accept="image/*">
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Update Car</button>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>




