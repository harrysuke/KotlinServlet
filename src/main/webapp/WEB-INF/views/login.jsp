<%--
  Created by IntelliJ IDEA.
  User: khairi
  Date: 12/05/2026
  Time: 11:35 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Login</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <h2>Login</h2>
    <form action="login" method="post">
      Username: <input type="text" class="form-control" name="username" required /><br /><br />
      Password: <input type="password" class="form-control" name="password" required /><br /><br />
      <input type="submit" class="btn btn-primary" value="Login" />
    </form>
    <p>${error != null ? error : ""}</p>
    <p>New user? <a href="register">Register here</a></p>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
