<%@ page contentType="text/html;charset=UTF-8" %>
    <% String loggedUser=(session !=null) ? (String) session.getAttribute("loggedUser") : null; if (loggedUser==null) {
        response.sendRedirect("login"); return; } %>
        <html>

        <head>
            <title>Generate Report</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <%@ include file="navbar.jsp" %>
                <div class="container-fluid">
                    <div class="row">
                        <%@ include file="sidebar.jsp" %>
                            <div class="col-md-10 p-4">
                                <h2>Generate Contact Report</h2>
                                <form action="excel-report" method="get" class="mb-3">
                                    <div class="mb-3">
                                        <label>Start Date:</label>
                                        <input type="date" name="startDate" class="form-control" required />
                                    </div>
                                    <div class="mb-3">
                                        <label>End Date:</label>
                                        <input type="date" name="endDate" class="form-control" required />
                                    </div>
                                    <button type="submit" class="btn btn-success">Download Excel</button>
                                </form>
                                <form action="pdf-report" method="get">
                                    <div class="mb-3">
                                        <label>Start Date:</label>
                                        <input type="date" name="startDate" class="form-control" required />
                                    </div>
                                    <div class="mb-3">
                                        <label>End Date:</label>
                                        <input type="date" name="endDate" class="form-control" required />
                                    </div>
                                    <button type="submit" class="btn btn-primary">Download PDF</button>
                                </form>
                            </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>