<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/13/2025
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  .footer {
    background: #002855;
    padding: 20px;
    text-align: center;
    color: #FFFFFF;
  }
  .footer img {
    width: 100px; /* Adjust size as needed */
    margin-bottom: 10px;
  }
  .footer-links {
    margin: 10px 0;
  }
  .footer-links a {
    color: #FFD700;
    text-decoration: none;
    margin: 0 10px;
  }
  .footer-links a:hover {
    text-decoration: underline;
  }
  .footer p {
    margin: 5px 0;
  }
</style>

<div class="footer">
  <img src="<%= request.getContextPath() %>/assets/logo.png" alt="Mega City Cab Logo">

  <p>&copy; 2025 Mega City Cab. All rights reserved.</p>

  <div class="footer-links">
    <a href="about.jsp">About Us</a>
    <a href="contact.jsp">Contact</a>
    <a href="terms.jsp">Terms & Conditions</a>
    <a href="privacy.jsp">Privacy Policy</a>
  </div>

  <p>📍 123 Main Street, Mega City | 📞 +1 234 567 890 | ✉ support@megacitycab.com</p>
</div>


