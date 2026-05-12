<%--
  Created by IntelliJ IDEA.
  User: khairi
  Date: 12/05/2026
  Time: 11:23 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.User" %>
<%@ page import="java.util.List" %>
<% List<User> users = (List<User>) request.getAttribute("users"); %>
<html>
<head>
    <title>user list</title>
</head>
<body>
<a href="user-form.jsp">Add new user</a>
<table>
    <tr>
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
            <a href="user-edit?id=<%= user.getId()%>">Edit</a>
            <a href="user-delete?id=<%= user.getId()%>" onclick="return confirm("Are you sure?")">Delete</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
