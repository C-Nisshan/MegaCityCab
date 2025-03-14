<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 2/12/2025
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.megacitycab.megacitycab.models.Booking" %>
<html>
    <head>
        <title>Admin Dashboard</title>
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
            <!-- Main Content -->
            <div class="content">
                <h2>Welcome to Admin Dashboard</h2>
            </div>
        </div>

    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/footer.jsp" />

    </body>
</html>

