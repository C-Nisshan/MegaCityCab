<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/13/2025
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Us - Mega City Cab</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="container">
    <div class="main-container">

        <div class="row align-items-center">
            <!-- Left Side: Contact Form -->
            <div class="col-md-6">
                <h1>Contact Mega City Cab</h1>
                <p>Have any questions or need assistance? Reach out to us using the form below.</p>
                <form action="contact" method="post">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="message">Message:</label>
                        <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Send Message</button>
                </form>
            </div>

            <!-- Right Side: Contact Details -->
            <div class="col-md-6 text-center">
                <img src="<%= request.getContextPath() %>/assets/contact-us.jpg" alt="Contact Us" class="contact-image">
                <h3>Our Contact Information</h3>
                <p><strong>Phone:</strong> +94 77 123 4567</p>
                <p><strong>Email:</strong> support@megacitycab.com</p>
                <p><strong>Address:</strong> 123, Main Street, Colombo, Sri Lanka</p>
            </div>
        </div>

    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
    }
    .main-container {
        max-width: 1000px;
        margin: 40px auto;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    h1, h3 {
        color: #004D99;
        margin-bottom: 20px;
        text-align: center;
    }
    p {
        font-size: 16px;
        color: #333;
        line-height: 1.6;
    }
    .contact-image {
        width: 100%;
        height: auto;
        border-radius: 10px;
        max-width: 450px;
    }
</style>

</html>
