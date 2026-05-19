import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import java.io.PrintWriter
import com.example.doa.UserDAO
import com.example.model.User

@WebServlet("/user2")
class IndexServlet2 : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        req.getRequestDispatcher("/WEB-INF/views/userCrud.jsp").forward(req, res)
    }
}


@WebServlet("/user-insert2")
class HtmxServlet : HttpServlet() {
    private val userDAO = UserDAO()
    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        val fullName = req.getParameter("fullName") ?: ""
        val mobileNumber = req.getParameter("mobileNumber") ?: ""
        val emailId = req.getParameter("emailId") ?: ""
        val country = req.getParameter("country") ?: ""

        val user = User(id = 0, fullName = fullName, mobileNumber = mobileNumber, emailId = emailId, country = country)

        if (userDAO.insertUser(user)) {
            res.status = HttpServletResponse.SC_OK
            res.setHeader("HX-Trigger", "refreshUserList")
            res.writer.write("User added successfully")
        } else {
            res.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            res.writer.write("Failed to add user")
        }
    }

    @WebServlet("/user-edit2")
    class UpdateServlet: HttpServlet() {
        private val userDAO = UserDAO()

        override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
            val id = req.getParameter("id")?.toIntOrNull() ?: 0
            val user = userDAO.getUserById(id)
            res.contentType = "text/html"
            val writer = res.writer
            if (user != null) {
                writer.write("""
                    <form id="editUserForm" hx-post="${req.contextPath}/user-update2" hx-target="this" hx-swap="none"
                        hx-on:after-request="document.getElementById('editContainer').style.display='none'; htmx.trigger('user-table-body','refreshUserList')">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Name</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" required>
                        </div>
                        <div class="mb-3">
                            <label for="mobileNumber" class="form-label">Mobile</label>
                            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="${user.mobileNumber}" required>
                        </div>
                        <div class="mb-3">
                            <label for="emailId" class="form-label">Email</label>
                            <input type="email" class="form-control" id="emailId" name="emailId" value="${user.emailId}" required>
                        </div>
                        <div class="mb-3">
                            <label for="country" class="form-label">Country</label>
                            <input type="text" class="form-control" id="country" name="country" value="${user.country}" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Update</button>
                        <button type="button" class="btn btn-secondary" onclick="document.getElementById('editContainer').style.display='none'">Cancel</button>
                    </form>
                """.trimIndent())
            } else {
                res.status = HttpServletResponse.SC_NOT_FOUND
                writer.write("User not found")
            }
        }

        @WebServlet("/user-update2")
        class UpdateActionServlet : HttpServlet() {
            private val userDAO = UserDAO()

            override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
                val id = req.getParameter("id")?.toIntOrNull() ?: 0
                val fullName = req.getParameter("fullName") ?: ""
                val mobileNumber = req.getParameter("mobileNumber") ?: ""
                val emailId = req.getParameter("emailId") ?: ""
                val country = req.getParameter("country") ?: ""

                val user = User(id = id, fullName = fullName, mobileNumber = mobileNumber, emailId = emailId, country = country)

                if (userDAO.updateUser(user)) {
                    res.status = HttpServletResponse.SC_OK
                    res.setHeader("HX-Trigger", "refreshUserList")
                    res.writer.write("User updated successfully")
                } else {
                    res.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                    res.writer.write("Failed to update user")
                }
            }
        }
    }

    @WebServlet("/user-list2")
    class ListServlet : HttpServlet() {
    private val userDAO = UserDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val users = userDAO.getAllUsers()
        res.contentType = "text/html"
        val writer = res.writer

        if (users.isEmpty()){
            writer.write("<tr><td colspan=\"6\" class=\"text-center text-muted py-3\">No users found</td></tr>")
            return
        }

        for (user in users){
            writer.write("""
            <tr>
                <td scope="row" class="align-middle">${user.id}</td>
                <td class="align-middle">${escapeHtml(user.fullName)}</td>
                <td class="align-middle">${escapeHtml(user.mobileNumber)}</td>
                <td class="align-middle">${escapeHtml(user.emailId)}</td>
                <td class="align-middle">${escapeHtml(user.country)}</td>
                <td class="align-middle">
                    <button class="btn btn-sm btn-danger me-1" 
                            hx-delete="${req.contextPath}/user-delete2?id=${user.id}" 
                            hx-target="closest tr" 
                            hx-swap="outerHTML"
                            hx-confirm="Delete this user?" 
                            hx-trigger="click">
                        <i class="bi bi-trash"></i> Delete
                    </button>
                    <button onClick="loadEditForm(${user.id})" class="btn btn-sm btn-secondary">
                        <i class="bi bi-pencil"></i> Edit
                    </button>
                </td>
            </tr>
            """.trimIndent())
            }
        }

        private fun escapeHtml(s: String): String {
            return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
        }
    }


    @WebServlet("/user-delete2")
    class DeleteServlet: HttpServlet() {
        private val userDAO = UserDAO()

        override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
            val id = req.getParameter("id")?.toIntOrNull() ?: 0
            if (userDAO.deleteUser(id)) {
                res.status = HttpServletResponse.SC_OK
                res.setHeader("HX-Trigger", "refreshUserList")
                res.writer.write("User deleted successfully")
            } else {
                res.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                res.writer.write("Failed to delete user")
            }
        }
    }
}