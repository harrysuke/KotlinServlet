package com.example.servlet

import com.example.doa.UserDAO
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/user-list")
class ReadServlet : HttpServlet() {
    private val userDAO = UserDAO()

        override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
            val users = userDAO.getAllUsers()
            req.setAttribute("users", users)
            req.getRequestDispatcher("/user-list.jsp").forward(req, res)
        }
}