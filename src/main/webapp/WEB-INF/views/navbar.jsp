<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .navbar {
        background: #001F3F; /* Darker shade of blue */
        position: sticky;
        top: 0;
        z-index: 1000;
        width: 100%;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Adds a subtle shadow */
        border-bottom: 2px solid #cccccc; /* Light gray */
        background: linear-gradient(to right, #002855, #003F6B);
    }

    .navbar-brand {
        font-size: 24px;
        font-weight: bold;
        color: #FFFFFF !important;
        display: flex;
        align-items: center;
    }

    .navbar-brand img {
        height: 60px;
        margin-right: 10px;
    }

    .nav-link {
        color: #FFFFFF !important;
        transition: 0.3s;
    }

    .nav-link:hover {
        background: #FFFFFF;
        color: #004D99 !important;
        border-radius: 5px;
    }
</style>

<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    String dashboardLink = "#";

    if ("Admin".equals(role)) {
        dashboardLink = request.getContextPath() + "/admin/dashboard";
    } else if ("Customer".equals(role)) {
        dashboardLink = request.getContextPath() + "/customer/dashboard";
    } else if ("Driver".equals(role)) {
        dashboardLink = request.getContextPath() + "/driver/dashboard";
    }
%>

<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/home.jsp">
            <img src="<%= request.getContextPath() %>/assets/logo.png" alt="Logo">
            Mega City Cab
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/booking">Book a Cab</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/about">About us</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/contact">Contact us</a></li>
                <% if (username != null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= dashboardLink %>">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/logout">Logout</a>
                    </li>
                    <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/login.jsp">Login</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
