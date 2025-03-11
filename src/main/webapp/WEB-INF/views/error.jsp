<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h2>An error occurred</h2>
<p><%= request.getAttribute("error") %></p>
</body>
</html>
