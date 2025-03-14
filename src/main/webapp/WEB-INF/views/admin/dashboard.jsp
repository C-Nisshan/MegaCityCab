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
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="dashboard-container">
    <h2 class="text-center">Admin Dashboard</h2>

    <h4 class="mt-4">Manage All Bookings</h4>
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

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

<script>
    function showDetails(bookingId) {
        alert("Showing details for booking ID: " + bookingId);
    }
</script>

