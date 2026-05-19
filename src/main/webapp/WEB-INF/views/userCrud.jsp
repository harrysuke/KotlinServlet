<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.example.model.User" %>
        <%@ page import="jakarta.servlet.http.HttpSession" %>
            <%@ page import="java.util.List" %>
                <% List<User> users = (List<User>) request.getAttribute("users");
                        String loggedUser = (String) session.getAttribute("loggedUser");
                        if (loggedUser == null) response.sendRedirect("login");
                        %>
                        <html>

                        <head>
                            <title>User CRUD</title>
                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
                                rel="stylesheet"
                                integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                                crossorigin="anonymous" />
                            <link rel="stylesheet"
                                href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
                        </head>

                        <body>
                            <!-- Include Navbar -->
                            <%@ include file="navbar.jsp" %>

                                <div class="container-fluid">
                                    <div class="row">
                                        <!-- Sidebar -->
                                        <%@ include file="sidebar.jsp" %>

                                            <!-- Main Content -->
                                            <div class="col-md-10 p-4">
                                                <h1>User Management</h1>
                                                <form id="addUserForm"
                                                    hx-post="${pageContext.request.contextPath}/user-insert2"
                                                    hx-target="this" hx-swap="none"
                                                    hx-on:after-request="this.reset(); htmx-trigger('#user-table-body','refreshUserList')">
                                                    <div class="mb-3">
                                                        <label for="fullName" class="form-label">Name</label>
                                                        <input type="text" class="form-control" id="fullName"
                                                            name="fullName" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="mobile" class="form-label">Mobile</label>
                                                        <input type="text" class="form-control" id="mobileNumber"
                                                            name="mobileNumber" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="email" class="form-label">Email</label>
                                                        <input type="email" class="form-control" id="emailId"
                                                            name="emailId" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="country" class="form-label">Country</label>
                                                        <input type="text" class="form-control" id="country"
                                                            name="country" required>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Save</button>
                                                </form>

                                                <!--Edit user container (hiden until edit is clicked)-->
                                                <div id="editContainer" class="edit-container">
                                                    <h1>Edit User</h1>
                                                    <div id="editFormPlaceholder"></div>
                                                    <button class="btn btn-secondary"
                                                        onclick="document.getElementById('editContainer').style.display='none'">Cancel</button>
                                                </div>

                                                <!--User list (loaded via htmx)-->
                                                <!-- user list container -->
                                                <div id="userListContainer">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Name</th>
                                                                <th>Mobile</th>
                                                                <th>Email</th>
                                                                <th>Country</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>
                                                        <!-- HTMX targets this tbody directly -->
                                                        <tbody id="userTableBody" hx-get="/user-list2"
                                                            hx-trigger="load">
                                                            <!-- Rows will load here dynamically -->
                                                        </tbody>
                                                    </table>
                                                </div>


                                            </div>
                                    </div>
                                </div>
                                <script
                                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
                                    integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
                                    crossorigin="anonymous"></script>
                                <script src="https://unpkg.com/htmx.org@2.0.2/dist/htmx.min.js"></script>
                                <script>
                                    //function to show edit container and load edit form
                                    window.loadEditForm = function (UserId) {
                                        const container = document.getElementById('editContainer');
                                        const placeholder = document.getElementById('editFormPlaceholder');
                                        container.style.display = 'block';
                                        placeholder.innerHTML = '<div class="text-center">loading...</div>';
                                        //load edit form via HTMX
                                        htmx.ajax('GET', "${pageContext.request.contextPath}/user-edit2?id=" + UserId, { target: '#editFormPlaceholder', swap: 'innerHTML' });
                                    }
                                </script>
                        </body>

                        </html>