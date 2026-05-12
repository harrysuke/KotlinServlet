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
</head>
<body>
<h1><%= isEdit ? "Edit User" : "Add User"%></h1>

<form action="<%= isEdit ? "user-update" : "user-insert"%>" method="post">
    <%-- Fix: Added the missing '{' after the if statement --%>
    <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= user.getId() %>">
    <% } %>

    <p>Name:</p>
    <input type="text" name="fullName" value="<%= isEdit ? user.getFullName() : ""%>" required/>
    
    <p>Mobile:</p>
    <input type="text" name="mobileNumber" value="<%= isEdit ? user.getMobileNumber() : ""%>" required/>
    
    <p>Email:</p>
    <input type="text" name="emailId" value="<%= isEdit ? user.getEmailId() : ""%>" required/>
    
    <p>Country:</p>
    <input type="text" name="country" value="<%= isEdit ? user.getCountry() : ""%>" required/>
    
    <br><br>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
