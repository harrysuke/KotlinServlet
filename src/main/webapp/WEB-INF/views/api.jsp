<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %> 
<% 
String loggedUser = (String) session.getAttribute("loggedUser"); 
if (loggedUser == null) response.sendRedirect("login");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Testing the REST API</title>
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
    <h1>Testing the REST API</h1>

    <h2>1. Get All Users</h2>
    <ul>
        <li><strong>Method:</strong> GET</li>
        <li><strong>URL:</strong> <code>http://localhost:8080/api/users</code></li>
    </ul>

    <h2>2. Create a User</h2>
    <ul>
        <li><strong>Method:</strong> POST</li>
        <li><strong>URL:</strong> <code>http://localhost:8080/api/users</code></li>
        <li><strong>Headers:</strong> <code>Content-Type: application/json</code></li>
    </ul>
    <p><strong>Request Body:</strong></p>
<pre><code>{
  "name": "Alice",
  "email": "alice@example.com",
  "country": "USA"
}</code></pre>

    <h2>3. Update a User</h2>
    <ul>
        <li><strong>Method:</strong> PUT</li>
        <li><strong>URL:</strong> <code>http://localhost:8080/api/users/1</code></li>
        <li><strong>Headers:</strong> <code>Content-Type: application/json</code></li>
    </ul>
    <p><strong>Request Body:</strong></p>
<pre><code>{
  "name": "Alice mith",
  "email": "alice@example.com",
  "country": "Canada"
}</code></pre>

    <h2>4. Delete a User</h2>
    <ul>
        <li><strong>Method:</strong> DELETE</li>
        <li><strong>URL:</strong> <code>http://localhost:8080/api/users/2</code></li>
    </ul>
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
