<%--
  Created by IntelliJ IDEA.
  User: khairi
  Date: 12/05/2026
  Time: 11:35 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="jakarta.servlet.http.HttpSession" %> 
<% 
String loggedUser = (String) session.getAttribute("loggedUser"); 
if (loggedUser == null) response.sendRedirect("login");
%>
<html>
  <head>
    <title>User Management</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <!-- Top Navigation Bar -->
    <!-- Include Navbar -->
    <%@ include file="navbar.jsp" %>

    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <%@ include file="sidebar.jsp" %>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
          <h1>User Management</h1>
          <p>Welcome to the user management system.</p>
        </div>
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
