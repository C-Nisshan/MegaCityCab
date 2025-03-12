<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 2/27/2025
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacitycab.megacitycab.models.Car" %>
<html>
    <head>
        <title>sidebar</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                display: flex;
            }
            .sidebar {
                width: 250px;
                background-color: #333;
                color: white;
                padding: 15px;
                height: 100vh;
            }
            .sidebar a {
                display: block;
                padding: 10px;
                color: white;
                text-decoration: none;
                margin-bottom: 5px;
            }
            .sidebar a:hover {
                background-color: #555;
            }
            .content {
                flex: 1;
                padding: 20px;
            }
        </style>
    </head>
    <body>
    <div class="sidebar">
        <h3>Admin Panel</h3>
        <a href="/MegaCityCab_war_exploded/admin/dashboard">Dashboard</a>
        <a href="/MegaCityCab_war_exploded/admin/manage_cars">Car Management</a>
        <a href="/MegaCityCab_war_exploded/admin/manage_drivers">Driver Management</a>
        <a href="/MegaCityCab_war_exploded/admin/manage_customers">Customer Management</a>
        <a href="/MegaCityCab_war_exploded/admin/manage_locations">Location Management</a>
        <a href="/MegaCityCab_war_exploded/admin/manage_pricing">Pricing Management</a>
        <a href="/MegaCityCab_war_exploded/admin/driver_assignment">Assign Driver</a>
    </div>
    </body>
</html>
