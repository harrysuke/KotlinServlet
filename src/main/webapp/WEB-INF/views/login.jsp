<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:35 am --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
          crossorigin="anonymous"/>
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg p-4" style="width: 400px;">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Login</h2>
            
            <form action="login" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" id="username" class="form-control" name="username" required>
                </div>
                
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" id="password" class="form-control" name="password" required>
                </div>
                
                <div class="d-grid">
                    <input type="submit" class="btn btn-primary" value="Login">
                </div>
            </form>
            
            <!-- Error message -->
            <p class="text-danger text-center mt-3">${error != null ? error : ""}</p>
            
            <!-- Register link -->
            <p class="text-center mt-3">
                New user? <a href="register" class="text-decoration-none">Register here</a>
            </p>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" 
        crossorigin="anonymous"></script>
</body>
</html>
