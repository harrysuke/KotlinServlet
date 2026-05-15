<%-- Created by IntelliJ IDEA. User: khairi Date: 12/05/2026 Time: 11:35 am To change this template use File | Settings
    | File Templates. --%>
    <%@ page contentType="text/html;charset=UTF-8" %>
        <%@ page import="com.example.model.User" %>
            <%@ page import="jakarta.servlet.http.HttpSession" %>
                <%@ page import="java.util.List" %>
                    <% List<User> users = (List<User>) request.getAttribute("users");
                            String loggedUser = (String) session.getAttribute("loggedUser");
                            if (loggedUser == null) response.sendRedirect("login");
                            %>
                            <html>

                            <head>
                                <title>Generate PDF Report</title>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
                                    rel="stylesheet"
                                    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                                    crossorigin="anonymous">
                            </head>

                            <body>
                                <%@ include file="navbar.jsp" %>
                                    <div class="container-fluid">
                                        <div class="row">
                                            <!-- Sidebar -->
                                            <%@ include file="sidebar.jsp" %>

                                                <!-- Main Content -->
                                                <div class="col-md-10 p-4">
                                                    <h2>Generate Contact Report (PDF)</h2>
                                                    <form action="pdf-report" method="get">
                                                        Start Date: <input type="date" class="form-control"
                                                            name="startDate" required /><br /><br />
                                                        End Date: <input type="date" class="form-control" name="endDate"
                                                            required /><br /><br />
                                                        <input type="submit" value="Generate PDF"
                                                            class="btn btn-primary" />
                                                    </form>
                                                </div>
                                        </div>
                                    </div>
                                    <script
                                        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
                                        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
                                        crossorigin="anonymous"></script>
                            </body>

                            </html>