<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:23 am To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="com.example.model.User" %> 
<%@ page import="jakarta.servlet.http.HttpSession" %> 
<%@ page import="java.util.List" %> 
<%
  List<User> users = (List<User>) request.getAttribute("users");  
  String loggedUser = (String) session.getAttribute("loggedUser");
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
      </head>
      <body>
        <p>Logged in as: <%= loggedUser %> | <a href="${pageContext.request.contextPath}/">Home</a> <a href="logout">Logout</a></p>
        <a href="${pageContext.request.contextPath}/add-user">Add new user</a>
        <table class="table">
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
              <a
                href="user-delete?id=<%= user.getId()%>"
                onclick="return confirm('Are you sure?')"
                >Delete</a
              >
            </td>
          </tr>
          <% } %>
        </table>
        <script
          src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"
        ></script>
      </body>
    </html> </User
></User>
