<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 2/12/2025
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacitycab.megacitycab.models.Booking" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #f8f9fa;
        }
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
        .dashboard-container {
            max-width: 900px;
            margin: 40px auto;
            padding: 20px;
            background: white;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .booking-table {
            margin-top: 20px;
        }
        .booking-table th {
            background: #007bff;
            color: white;
        }
        .status-completed {
            color: green;
            font-weight: bold;
        }
        .status-pending {
            color: orange;
            font-weight: bold;
        }
        .status-cancelled {
            color: red;
            font-weight: bold;
        }
        .accept-button {
            background-color: #12a51c;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .accept-button:hover {
            background-color: #12ac52;
            transform: scale(1.05);
        }
        .reject-button {
            background-color: #f43f3f;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .reject-button:hover {
            background-color: #fa0404;
            transform: scale(1.05);
        }
        .summary-box {
            color: white;
            text-align: center;
            padding: 20px;
            border-radius: 10px;
            margin: 20px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .summary{
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
        <div class="summary row">
            <div class="col-md-4">
                <div class="summary-box bg-primary">
                    <h4>Total Pending Bookings</h4>
                    <p><%= request.getAttribute("totalPendingBookings") %></p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="summary-box bg-danger">
                    <h4>Total Cancelled Bookings</h4>
                    <p><%= request.getAttribute("totalCancelledBookings") %></p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="summary-box bg-success">
                    <h4>Total Confirmed Bookings</h4>
                    <p><%= request.getAttribute("totalConfirmedBookings") %></p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="summary-box bg-warning">
                    <h4>Total Processing Bookings</h4>
                    <p><%= request.getAttribute("totalProcessingBookings") %></p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="summary-box bg-info">
                    <h4>Total Cars</h4>
                    <p><%= request.getAttribute("totalCars") %></p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="summary-box bg-secondary">
                    <h4>Total Customers</h4>
                    <p><%= request.getAttribute("totalCustomers") %></p>
                </div>
            </div>
        </div>

        <div class="dashboard-container">
            <h4 class="mt-4 text-center">Manage All Bookings</h4>
            <table class="table table-bordered booking-table">
                <thead>
                <tr style="text-align: center;">
                    <th>Booking ID</th>
                    <th>Pickup</th>
                    <th>Dropoff</th>
                    <th>Fare (LKR)</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
                    if (bookings != null && !bookings.isEmpty()) {
                        for (Booking booking : bookings) {
                %>
                <tr style="text-align: center;">
                    <td><%= booking.getBookingId() %></td>
                    <td><%= booking.getPickupLocation().getLocationName() %></td>
                    <td><%= booking.getDropoffLocation().getLocationName() %></td>
                    <td><%= booking.getTotalAmount() %></td>
                    <td class="<%= booking.getStatus().equals("Completed") ? "status-completed" :
                            booking.getStatus().equals("Pending") ? "status-pending" : "status-cancelled" %>">
                        <%= booking.getStatus() %>
                    </td>
                    <td>
                        <button class="accept-button" onclick="showDetails('<%= booking.getBookingId() %>')">Accept</button>
                        <button class="reject-button" onclick="showDetails('<%= booking.getBookingId() %>')">Reject</button>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">No bookings found</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<script>
    function showDetails(bookingId) {
        alert("Showing details for booking ID: " + bookingId);
    }
</script>

