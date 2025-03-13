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
                background-color: #002855;
                color: white;
                padding: 20px;
                height: 100vh;
                position: sticky;
                top: 0;
                left: 0;
            }
            .sidebar a {
                display: block;
                padding: 12px;
                color: white;
                text-decoration: none;
                margin: 5px 0;
                border-radius: 5px;
                transition: background 0.3s, color 0.3s;
            }
            .sidebar a:hover {
                background-color: #FFFFFF;
                color: #004D99;
            }
            .sidebar a.active {
                background-color: #004D99;
                color: #FFFFFF;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
    <div class="sidebar">
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
