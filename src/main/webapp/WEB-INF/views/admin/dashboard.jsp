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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
        <div class="main-container">
            <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>
            <!-- Main Content -->
            <div class="content">
                <h2>Welcome to Admin Dashboard</h2>
            </div>
        </div>
    </body>
</html>

