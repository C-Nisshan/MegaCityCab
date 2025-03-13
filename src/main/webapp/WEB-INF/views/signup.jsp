<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/13/2025
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Sign Up - Mega City Cab</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="container">
    <div class="main-container">
        <div class="row justify-content-center">
            <!-- Right Side: Sign Up Form -->
            <div class="col-md-8">
                <h1>Create an Account</h1>
                <p>Join Mega City Cab and enjoy seamless rides.</p>

                <form method="post" action="signup" class="form-group">
                    <input type="hidden" name="action" value="create">
                    <input type="hidden" name="role" value="Customer">

                    <div class="row">
                        <!-- Column 1 -->
                        <div class="col-md-6">
                            <label>Username:</label>
                            <input type="text" name="username" class="form-control" required>

                            <label>Password:</label>
                            <input type="password" name="password" class="form-control" required>

                            <label>Name:</label>
                            <input type="text" name="name" class="form-control" required>

                            <label>Address:</label>
                            <input type="text" name="address" class="form-control" required>
                        </div>

                        <!-- Column 2 -->
                        <div class="col-md-6">
                            <label>NIC:</label>
                            <input type="text" name="nic" class="form-control" required>

                            <label>Phone:</label>
                            <input type="text" name="telephone" class="form-control" required>

                            <label>Email:</label>
                            <input type="email" name="email" class="form-control">
                        </div>
                    </div>

                    <br>
                    <button type="submit" class="btn btn-success btn-block">Sign Up</button>
                </form>

                <p class="mt-3" style="text-align: center">Already have an account? <a href="login.jsp">Login here</a></p>
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
    h1 {
        color: #004D99;
        text-align: center;
    }
    p {
        font-size: 14px;
        color: #595959;
        text-align: center;
    }
</style>

</html>

