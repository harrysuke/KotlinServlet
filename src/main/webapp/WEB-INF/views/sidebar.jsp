<div class="col-md-2 bg-light vh-100 p-3">
  <h5>Menu</h5>
  <ul class="nav flex-column">
    <% if (loggedUser !=null) { %>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="user-list">View all users</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="add-user">Add new user</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="user2">HTMX user list</a>
      </li>
      <li class="nav-item">
        <a class="nav-link dropdown-toggle" href="#reportsMenu" data-bs-toggle="collapse" role="button"
          aria-expanded="false" aria-controls="reportsMenu">
          Reports
        </a>
        <!-- Child Menu Items -->
        <div class="collapse" id="reportsMenu">
          <ul class="nav flex-column ps-3 small">
            <li class="nav-item">
              <a class="nav-link" href="search-form">Generate PDF report</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="search-excel">Generate XLS report</a>
            </li>
          </ul>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="api">REST API</a>
      </li>
      <% } else { %>
        <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
        <li class="nav-item">
          <a class="nav-link" href="register">Register</a>
        </li>
        <% } %>
  </ul>
</div>