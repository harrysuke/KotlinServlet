<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:23 am To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="com.example.model.User" %> <%@ page
import="jakarta.servlet.http.HttpSession" %> <%@ page import="java.util.List" %>
<% 
List<User> users = (List<User>) request.getAttribute("users"); 
String loggedUser = (String) session.getAttribute("loggedUser"); 
if (loggedUser == null) response.sendRedirect("login");
%>
    <html>
      <head>
        <title>user list</title>
        <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous"
        />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
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
                <li class="nav-item">
                  <a class="nav-link" href="login">Login</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="register">Register</a>
                </li>
                <% } %>
              </ul>
            </div>

            <!-- Main Content -->
            <div class="col-md-10 p-4">
              <h1>User list</h1>
              <table class="table">
                <tr>
                  <th>#</th>
                  <th>Fullname</th>
                  <th>Mobile</th>
                  <th>Email</th>
                  <th>Country</th>
                  <th>Actions</th>
                </tr>
                <% for (User user : users){ %>
                <tr>
                  <td><%= user.getId()%></td>
                  <td><%= user.getFullName()%></td>
                  <td><%= user.getMobileNumber()%></td>
                  <td><%= user.getEmailId()%></td>
                  <td><%= user.getCountry()%></td>
                  <td>
                    <a class="btn btn-link" href="user-edit?id=<%= user.getId()%>"><i class="bi bi-pencil"></i></a>
                    <a class="btn btn-link" 
                      href="user-delete?id=<%= user.getId()%>"
                      onclick="return confirm('Are you sure?')"
                      ><i class="bi bi-trash"></i></a
                    >
                  </td>
                </tr>
                <% } %>
              </table>
            </div>
          </div>
        </div>
        <script
          src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"
        ></script>
      </body>
    </html> </User
></User>
