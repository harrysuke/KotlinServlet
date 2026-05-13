<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">User Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#sidebar" aria-controls="sidebar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <% if (loggedUser != null) { %>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle text-white" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <%= loggedUser %>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/">Home</a></li>
              <li><a class="dropdown-item" href="user-list">View all users</a></li>
              <li><a class="dropdown-item" href="add-user">Add new user</a></li>
              <li><a class="dropdown-item" href="api">REST API</a></li>
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
