package com.example.servlet

import com.example.doa.UserDAO
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/user-delete")
class DeleteServlet : HttpServlet() {
    private val userDAO = UserDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val id = req?.getParameter("id")?.toInt() ?: 0
        if (userDAO.deleteUser(id)) {
            res?.sendRedirect(req.contextPath + "/user-list")
        } else {
            res?.sendError(500, "Failed to delete record")
        }
    }
}