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
  <title>About Us - Mega City Cab</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="container">
  <div class="main-container">

    <!-- Row for Image and Description -->
    <div class="row align-items-center">
      <!-- Left Side: Image -->
      <div class="col-md-6 text-center">
        <img src="<%= request.getContextPath() %>/assets/about-us.jpg" alt="Mega City Cab" class="about-image">
      </div>

      <!-- Right Side: Description -->
      <div class="col-md-6">
        <h1>About Mega City Cab</h1>
        <p>
          Mega City Cab is a premier cab service operating in Colombo City, serving thousands of customers every month.
          Our mission is to provide a safe, reliable, and efficient transportation solution for residents and visitors.
        </p>
        <p>
          Previously, our operations were managed manually, but with growing demand, we have transitioned to a
          computerized system to streamline bookings, manage customer details, and calculate bills efficiently.
        </p>
      </div>
    </div>

    <!-- Additional Information Below -->
    <div class="row mt-5">
      <div class="col-12">
        <h3>Why Choose Mega City Cab?</h3>
        <ul>
          <li>Secure user authentication (Login & Logout)</li>
          <li>New customer registrations and bookings</li>
          <li>Easy booking management and order tracking</li>
          <li>Automated bill calculation with applicable taxes/discounts</li>
          <li>Comprehensive management of cars and drivers</li>
          <li>User-friendly help section for first-time users</li>
        </ul>
        <p>
          We are committed to delivering the best ride experience in Colombo City, ensuring convenience and efficiency
          for all our customers. Ride with Mega City Cab – Your Trusted Travel Partner!
        </p>
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
  .about-image {
    width: 100%;
    height: auto;
    border-radius: 10px;
    max-width: 450px; /* Ensure a reasonable max size */
  }
  ul {
    text-align: left;
    display: inline-block;
  }
</style>

</html>



