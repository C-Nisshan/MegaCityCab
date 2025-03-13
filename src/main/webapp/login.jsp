<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login - Mega City Cab</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="container">
    <div class="main-container">
        <div class="row" style="text-align: center; color: #004D99;">
            <div class="col-12">
                <h2>Login to Your Account</h2>
                <p>Welcome back! Please enter your credentials to continue.</p>
            </div>
        </div>
        <div class="row align-items-center">
            <!-- Left Side: Image -->
            <div class="col-md-6 text-center">
                <img src="<%= request.getContextPath() %>/assets/login.jpg" alt="Login" class="login-image">
            </div>

            <!-- Right Side: Login Form -->
            <div class="col-md-6">
                <form method="post" action="login" class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" class="form-control" required>
                    <br>
                    <label>Password:</label>
                    <input type="password" name="password" class="form-control" required>
                    <br>
                    <input type="submit" value="Login" class="btn btn-primary btn-block">
                </form>

                <% if (request.getParameter("error") != null) { %>
                <p class="text-danger">Invalid credentials, try again.</p>
                <% } %>

                <p class="mt-3" style="text-align: center;">New user? <a href="<%=request.getContextPath()%>/signup">Sign up here</a></p>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
    }
    .main-container {
        max-width: 800px;
        margin: 40px auto;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    p {
        font-size: 15px;
        color: #868686;
    }
    .login-image {
        width: 100%;
        height: auto;
        border-radius: 10px;
        max-width: 500px;
    }
</style>

</html>
