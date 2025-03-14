<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/12/2025
  Time: 4:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mega City Cab - Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }

        /* Hero Section */
        .hero {
            position: relative;
            background: url('<%= request.getContextPath() %>/assets/home-cover.png') no-repeat center center;
            background-size: cover;
            height: 500px;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            color: white;
            overflow: hidden;
        }

        .hero::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
        }

        .hero-content {
            position: relative;
            z-index: 2;
        }

        .hero h1 {
            font-size: 50px;
            font-weight: bold;
            text-shadow: 3px 3px 10px rgba(0,0,0,0.6);
            animation: fadeInDown 1.5s ease-in-out;
        }

        .hero p {
            font-size: 20px;
            opacity: 0.9;
            animation: fadeInUp 1.5s ease-in-out;
        }

        .btn-custom {
            background: #FFC107;
            border: none;
            padding: 12px 30px;
            font-size: 18px;
            color: black;
            font-weight: bold;
            text-transform: uppercase;
            border-radius: 50px;
            transition: 0.3s;
            box-shadow: 0 5px 10px rgba(0,0,0,0.2);
        }

        .btn-custom:hover {
            background: #FFA000;
            transform: scale(1.05);
        }

        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Features Section */
        .section {
            padding: 60px 20px;
            text-align: center;
        }

        .feature-box {
            padding: 30px;
            background: #f8f9fa;
            border-radius: 10px;
            transition: 0.3s;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .feature-box:hover {
            background: #007BFF;
            color: white;
            transform: scale(1.05);
        }

        .feature-box img {
            width: 80px;
            margin-bottom: 15px;
        }

        /* Footer */
        .footer {
            background: #343a40;
            color: white;
            padding: 20px;
            text-align: center;
        }

        /* Testimonials */
        .testimonial {
            background: #f8f9fa;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .testimonial p {
            font-style: italic;
            font-size: 18px;
        }

        .testimonial img {
            width: 80px;
            border-radius: 50%;
            margin-top: 15px;
        }

        /* Contact Section */
        .contact-form {
            background: #f8f9fa;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<!-- Hero Section -->
<div class="hero">
    <div class="hero-content">
        <h1>Fast & Reliable Cab Service in Colombo</h1>
        <p>Book your ride now with Mega City Cab</p>
        <a href="<%= request.getContextPath() %>/booking" class="btn btn-custom">Book a Ride</a>
    </div>
</div>

<!-- Features Section -->
<div class="container">
    <div class="row section">
        <div class="col-md-4">
            <div class="feature-box">
                <img src="<%= request.getContextPath() %>/assets/booking-icon.jpg" alt="Booking Icon">
                <h3>Easy Booking</h3>
                <p>Book a cab in just a few clicks.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="feature-box">
                <img src="<%= request.getContextPath() %>/assets/price-icon.png" alt="Price Icon">
                <h3>Affordable Prices</h3>
                <p>Enjoy the best rates in the city.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="feature-box">
                <img src="<%= request.getContextPath() %>/assets/driver-icon.png" alt="Driver Icon">
                <h3>Professional Drivers</h3>
                <p>Well-trained, courteous drivers.</p>
            </div>
        </div>
    </div>
</div>

<!-- Customer Testimonials -->
<div class="container section">
    <h2>What Our Customers Say</h2>
    <div id="testimonialCarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="testimonial">
                    <p>"Mega City Cab is the best! Always on time and great service!"</p>
                    <img src="<%= request.getContextPath() %>/assets/user-1.png" alt="User 1">
                    <h5>John Doe</h5>
                </div>
            </div>
            <div class="carousel-item">
                <div class="testimonial">
                    <p>"I love the affordability and safety of Mega City Cab!"</p>
                    <img src="<%= request.getContextPath() %>/assets/user-2.png" alt="User 2">
                    <h5>Sarah Lee</h5>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#testimonialCarousel" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#testimonialCarousel" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
</div>

<div class="container section">
    <div class="row">
        <!-- Why Choose Us -->
        <div class="col-md-6">
            <h2>Why Choose Us?</h2>
            <div class="feature-box">
                <h4>24/7 Availability</h4>
                <p>We are always here to serve you.</p>
            </div>
            <div class="feature-box">
                <h4>Safe & Secure Rides</h4>
                <p>Trained drivers with safety measures.</p>
            </div>
            <div class="feature-box">
                <h4>Easy Payments</h4>
                <p>Pay easily via cash or online.</p>
            </div>
        </div>

        <!-- Contact Us -->
        <div class="col-md-6">
            <h2>Contact Us</h2>
            <div class="contact-form">
                <form>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Your Name">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" placeholder="Your Email">
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="4" placeholder="Your Message"></textarea>
                    </div>
                    <button type="submit" class="btn btn-custom">Send Message</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>
