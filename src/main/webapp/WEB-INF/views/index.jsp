<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:21 am To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="jakarta.servlet.http.HttpSession" %> 
<% String loggedUser = (String) session.getAttribute("loggedUser"); %>
<html>
  <head>
    <title>user management</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <h1>User Management</h1>
    <% if (loggedUser != null) { %>
    <p>Logged in as: <%= loggedUser %> | <a href="${pageContext.request.contextPath}/">Home</a> <a href="logout">Logout</a></p>
    <a href="user-list">View all users</a>
    <a href="add-user">Add new user</a>
    <% } else { %>
    <a href="login">Login</a>
    <a href="register">Register</a>
    <% } %>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
