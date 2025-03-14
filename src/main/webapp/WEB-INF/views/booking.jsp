<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/13/2025
  Time: 7:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Car, com.megacitycab.megacitycab.models.Location" %>
<%
    List<Car> carList = (List<Car>) request.getAttribute("carList");
    List<Location> locations = (List<Location>) request.getAttribute("locations"); // Get locations from request
%>
<html>
<head>
    <title>Booking</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function openBookingModal(carId) {
            document.getElementById("carId").value = carId;
            $('#bookingModal').modal('show');
        }
    </script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="container mt-4">
    <div class="row">
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
                    <button class="btn btn-success btn-block" onclick="openBookingModal(<%= car.getCarId() %>)">Book Now</button>
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

<!-- Booking Modal -->
<div class="modal fade" id="bookingModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Book a Ride</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
                    <input type="hidden" name="action" value="create">
                    <input type="hidden" id="carId" name="carId">
                    <input type="hidden" id="totalAmount" name="totalAmount" value="1000">

                    <div class="form-group">
                        <label for="pickupLocation">Pickup Location:</label>
                        <select class="form-control" id="pickupLocation" name="pickupLocation" required>
                            <% for (Location loc : locations) { %>
                            <option value="<%= loc.getLocationId() %>"><%= loc.getLocationName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="dropoffLocation">Drop-off Location:</label>
                        <select class="form-control" id="dropoffLocation" name="dropoffLocation" required>
                            <% for (Location loc : locations) { %>
                            <option value="<%= loc.getLocationId() %>"><%= loc.getLocationName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="bookingDate">Booking Date & Time:</label>
                        <input type="datetime-local" class="form-control" id="bookingDate" name="bookingDate" required>
                    </div>

                    <button type="submit" class="btn btn-primary btn-block">Confirm Booking</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<script>
    $(document).ready(function () {
        function validateBookingForm() {
            let pickupId = $("#pickupLocation").val();
            let dropoffId = $("#dropoffLocation").val();
            let bookingDateTime = new Date($("#bookingDate").val());
            let currentDateTime = new Date();

            // Check if pickup and drop-off locations are the same
            if (pickupId === dropoffId) {
                alert("Pickup and drop-off locations cannot be the same.");
                return false;
            }

            // Check if booking date & time is in the future
            if (bookingDateTime <= currentDateTime) {
                alert("Please select a valid future date and time for booking.");
                return false;
            }

            return true;
        }

        // Validate before submitting the form
        $("#bookingModal form").submit(function (e) {
            if (!validateBookingForm()) {
                e.preventDefault(); // Prevent form submission if validation fails
            }
        });
    });
</script>

</body>
</html>

