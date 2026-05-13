<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:21 am --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="jakarta.servlet.http.HttpSession" %> <% String loggedUser = (String)
session.getAttribute("loggedUser"); %>
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
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/"
                >Home</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="user-list">View all users</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="add-user">Add new user</a>
            </li>
            <% } else { %>
            <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
            <li class="nav-item">
              <a class="nav-link" href="register">Register</a>
            </li>
            <% } %>
          </ul>
        </div>

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
