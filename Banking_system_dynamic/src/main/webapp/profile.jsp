<%@ page import="java.util.*" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <title>Profile Page</title>
   <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
  
</head>
<body>


  <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
  <p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
  <p><strong>Age:</strong> <%= session.getAttribute("age") %></p>
  
  <form action="LogoutServlet" method="post">
    <button type="submit">Log Out</button>
  </form>
</body>
</html>