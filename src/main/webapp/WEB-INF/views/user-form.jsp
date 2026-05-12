<%--
  Created by IntelliJ IDEA.
  User: khairi
  Date: 12/05/2026
  Time: 11:35 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.User"%>
<%
    // Fix: Capitalized User cast (was lowercase 'user')
    User user = (User) request.getAttribute("user");
    boolean isEdit = (user != null);
%>
<html>
<head>
    <title><%= isEdit ? "Edit User" : "Add User" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<h1><%= isEdit ? "Edit User" : "Add User"%></h1>

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
