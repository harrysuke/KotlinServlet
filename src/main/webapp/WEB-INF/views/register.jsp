<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:35 am --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
          crossorigin="anonymous"/>
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg p-4" style="width: 500px;">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Register</h2>
            
            <form action="register" method="post">
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" id="fullName" class="form-control" name="fullName" required>
                </div>
                
                <div class="mb-3">
                    <label for="mobileNumber" class="form-label">Mobile</label>
                    <input type="text" id="mobileNumber" class="form-control" name="mobileNumber" required>
                </div>
                
                <div class="mb-3">
                    <label for="emailId" class="form-label">Email</label>
                    <input type="email" id="emailId" class="form-control" name="emailId" required>
                </div>
                
                <div class="mb-3">
                    <label for="country" class="form-label">Country</label>
                    <input type="text" id="country" class="form-control" name="country" required>
                </div>
                
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" id="username" class="form-control" name="username" required>
                </div>
                
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" id="password" class="form-control" name="password" required>
                </div>
                
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input type="password" id="confirmPassword" class="form-control" name="confirmPassword" required>
                </div>
                
                <div class="d-grid">
                    <input type="submit" class="btn btn-success" value="Register">
                </div>
            </form>
            
            <!-- Error message -->
            <p class="text-danger text-center mt-3">${error != null ? error : ""}</p>
            
            <!-- Login link -->
            <p class="text-center mt-3">
                Already have an account? <a href="login" class="text-decoration-none">Login</a>
            </p>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" 
        crossorigin="anonymous"></script>
</body>
</html>
