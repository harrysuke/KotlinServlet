<%--
  Created by IntelliJ IDEA.
  User: khairi
  Date: 12/05/2026
  Time: 11:35 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.User"%>
<%@ page import="jakarta.servlet.http.HttpSession" %> 
<%
    // Fix: Capitalized User cast (was lowercase 'user')
    User user = (User) request.getAttribute("user");
    boolean isEdit = (user != null);
    String loggedUser = (String) session.getAttribute("loggedUser");
    if (loggedUser == null) response.sendRedirect("login");
%>
<html>
<head>
    <title><%= isEdit ? "Edit User" : "Add User" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">User Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <% if (loggedUser != null) { %>
          <!-- User Dropdown Menu -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle text-white" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <%= loggedUser %>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/">Home</a></li>
              <li><a class="dropdown-item" href="user-list">View all users</a></li>
              <li><a class="dropdown-item" href="add-user">Add new user</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item text-danger" href="logout">Logout</a></li>
            </ul>
          </li>
        <% } else { %>
          <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
          <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 bg-light vh-100 p-3">
            <h5>Menu</h5>
            <ul class="nav flex-column">
                <% if (loggedUser != null) { %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="user-list">View all users</a></li>
                <li class="nav-item"><a class="nav-link" href="add-user">Add new user</a></li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
                <% } %>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 p-4">
            <h1>New user</h1>
<form action="<%= isEdit ? "user-update" : "user-insert"%>" method="post">
    <%-- Fix: Added the missing '{' after the if statement --%>
    <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= user.getId() %>">
    <% } %>

    <div class="mb-3">
        <label class="form-label">Name:</label>
        <input type="text" name="fullName" class="form-control" value="<%= isEdit ? user.getFullName() : ""%>" required/>
    </div>
    <div class="mb-3">
        <label class="form-label">Mobile:</label>
        <input type="text" name="mobileNumber" class="form-control" value="<%= isEdit ? user.getMobileNumber() : ""%>" required/>
    </div>
    <div class="mb-3">
        <label class="form-label">Email:</label>
        <input type="text" name="emailId" class="form-control" value="<%= isEdit ? user.getEmailId() : ""%>" required/>
    </div>
    <div class="mb-3">
        <label class="form-label">Country:</label>
    <input type="text" name="country" class="form-control" value="<%= isEdit ? user.getCountry() : ""%>" required/>
    </div>
    <div class="mb-3">
    <input type="submit" class="btn btn-primary" value="Submit"/>
    </div>
</form>
</div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
