<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 2/12/2025
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Admin Dashboard</title>
    </head>
    <body>
        <div class="container">
            <!-- Sidebar
            <div class="sidebar">
                <h3>Admin Panel</h3>
                <a href="/MegaCityCab_war_exploded/admin/dashboard">Dashboard</a>
                <a href="/MegaCityCab_war_exploded/admin/manage_cars">Car Management</a>
            </div>
            -->

            <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>
            <!-- Main Content -->
            <div class="content">
                <h2>Welcome to Admin Dashboard</h2>

                <!-- Include Manage Cars Section -->
                <!-- jsp:include page="/WEB-INF/views/admin/manage_cars.jsp" /-->
            </div>
        </div>
    </body>
</html>