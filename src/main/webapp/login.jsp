<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form method="post" action="login">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
<% if (request.getParameter("error") != null) { %>
<p style="color:red;">Invalid credentials, try again.</p>
<% } %>
</body>
</html>
