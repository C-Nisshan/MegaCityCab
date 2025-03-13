<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Car, com.megacitycab.megacitycab.models.Driver" %>
<%
    List<Car> carList = (List<Car>) request.getAttribute("carList");
    List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");
%>
<html>
<head>
    <title>Assign Driver</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .main-container {
            display: flex;
        }
        .sidebar {
            width: 250px;
            flex-shrink: 0;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="main-container">
    <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>

    <div class="content">
        <h2>Driver Assignment</h2>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>Car ID</th>
                <th>Registration Number</th>
                <th>Make</th>
                <th>Model</th>
                <th>Year</th>
                <th>Capacity</th>
                <th>Status</th>
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
                <td>
                    <% if ("unavailable".equalsIgnoreCase(car.getStatus())) { %>
                    <% if (car.getAssignedDriver() != null) { %>
                    <button class="btn btn-warning update-btn"
                            data-carid="<%= car.getCarId() %>"
                            data-driverid="<%= car.getAssignedDriver().getDriverId() %>"
                            data-drivername="<%= car.getAssignedDriver().getUser().getName() %>"
                            data-license="<%= car.getAssignedDriver().getLicenseNumber() %>"
                            data-toggle="modal" data-target="#assignDriverModal">
                        Update
                    </button>
                    <% } %>
                    <% } else { %>
                    <% if (car.getAssignedDriver() != null) { %>
                    <!-- If a driver is assigned, show "Update" button -->
                    <button class="btn btn-warning update-btn"
                            data-carid="<%= car.getCarId() %>"
                            data-driverid="<%= car.getAssignedDriver().getDriverId() %>"
                            data-drivername="<%= car.getAssignedDriver().getUser().getName() %>"
                            data-license="<%= car.getAssignedDriver().getLicenseNumber() %>"
                            data-toggle="modal" data-target="#assignDriverModal">
                        Update
                    </button>
                    <% } else { %>
                    <!-- If no driver is assigned, show "Assign Driver" button -->
                    <button class="btn btn-primary assign-btn" data-carid="<%= car.getCarId() %>"
                            data-toggle="modal" data-target="#assignDriverModal">
                        Assign Driver
                    </button>
                    <% } %>
                    <% } %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal for Driver Selection -->
<div class="modal fade" id="assignDriverModal" tabindex="-1" role="dialog" aria-labelledby="assignModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Select a Driver</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Assigned Driver Info (if any) -->
                <div id="assignedDriverSection" class="mb-3 d-none">
                    <h5>Currently Assigned Driver</h5>
                    <p><strong>Name:</strong> <span id="assignedDriverName"></span></p>
                    <p><strong>License:</strong> <span id="assignedDriverLicense"></span></p>
                    <button id="removeDriverBtn" class="btn btn-danger">Remove</button>
                </div>

                <!-- Available Drivers Table -->
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>License</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <%
                        List<Integer> assignedDriverIds = (List<Integer>) request.getAttribute("assignedDriverIds");
                    %>
                    <tbody>
                    <% for (Driver driver : drivers) { %>
                    <% if (!assignedDriverIds.contains(driver.getDriverId())) { %>
                    <tr>
                        <td><%= driver.getUser().getName() %></td>
                        <td><%= driver.getLicenseNumber() %></td>
                        <td>
                            <button class="btn btn-success select-driver"
                                    data-driverid="<%= driver.getDriverId() %>"
                                    data-drivername="<%= driver.getUser().getName() %>"
                                    data-license="<%= driver.getLicenseNumber() %>">
                                Assign
                            </button>
                        </td>
                    </tr>
                    <% } %>
                    <% } %>
                    </tbody>
                </table>

                <!-- Selected Driver Card -->
                <div id="selectedDriverCard" class="card mt-3 d-none">
                    <div class="card-body">
                        <h5 class="card-title">Selected Driver</h5>
                        <p><strong>Name:</strong> <span id="selectedDriverName"></span></p>
                        <p><strong>License:</strong> <span id="selectedDriverLicense"></span></p>
                        <form id="assignForm" method="post" action="">
                            <input type="hidden" name="action" value="assign">
                            <input type="hidden" name="carId" id="selectedCarId">
                            <input type="hidden" name="driverId" id="selectedDriverId">
                            <button type="submit" class="btn btn-primary">Assign</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $(".assign-btn, .update-btn").click(function () {
            var carId = $(this).data("carid");
            var driverId = $(this).data("driverid");
            var driverName = $(this).data("drivername");
            var driverLicense = $(this).data("license");

            $("#selectedCarId").val(carId);

            if (driverId) {
                // Show currently assigned driver
                $("#assignedDriverName").text(driverName);
                $("#assignedDriverLicense").text(driverLicense);
                $("#assignedDriverSection").removeClass("d-none");
                $("#removeDriverBtn").data("carid", carId).data("driverid", driverId);
            } else {
                $("#assignedDriverSection").addClass("d-none");
            }
        });

        $(".select-driver").click(function () {
            var driverId = $(this).data("driverid");
            var driverName = $(this).data("drivername");
            var driverLicense = $(this).data("license");

            $("#selectedDriverName").text(driverName);
            $("#selectedDriverLicense").text(driverLicense);
            $("#selectedDriverId").val(driverId);
            $("#selectedDriverCard").removeClass("d-none");
        });

        $("#removeDriverBtn").click(function () {
            var carId = $(this).data("carid");
            var driverId = $(this).data("driverid");

            if (!confirm("Are you sure you want to remove this driver?")) return;

            $.ajax({
                url: "/MegaCityCab_war_exploded/admin/driver_assignment",
                type: "POST",
                data: { action: "remove", carId: carId, driverId: driverId },
                success: function (response) {
                    if (response.includes("success")) {
                        alert("Driver removed successfully.");
                        location.reload();
                    } else {
                        alert("Failed to remove driver.");
                    }
                },
                error: function () {
                    alert("Error removing driver.");
                }
            });
        });

        $("#assignForm").submit(function (e) {
            e.preventDefault();

            var carId = $("#selectedCarId").val();
            var driverId = $("#selectedDriverId").val();

            if (!carId || !driverId) {
                alert("Please select a driver before assigning.");
                return;
            }

            $.ajax({
                url: "/MegaCityCab_war_exploded/admin/driver_assignment",
                type: "POST",
                data: { action: "assign", carId: carId, driverId: driverId },
                success: function (response) {
                    if (response.includes("success")) {
                        alert("Driver assigned successfully.");
                        location.reload();
                    } else {
                        alert("Failed to assign driver.");
                    }
                },
                error: function () {
                    alert("Error assigning driver.");
                }
            });
        });
    });
</script>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>
