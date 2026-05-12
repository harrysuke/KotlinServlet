package com.example.servlet

import com.example.doa.UserDAO
import com.example.model.User
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/user-edit")
class UpdateServlet : HttpServlet() {
    private val userDAO: UserDAO = UserDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val id = req.getParameter("id").toInt()
        val user = userDAO.getUserById(id)
        req.setAttribute("user", user)
        req.getRequestDispatcher("/user-form.jsp").forward(req, res)
    }
}

@WebServlet("/user-update")
class UpdateActionServlet : HttpServlet() {
    private val userDAO: UserDAO = UserDAO()

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        val id = req.getParameter("id").toInt()
        val fullName = req.getParameter("fullName")
        val mobileNumber = req.getParameter("mobileNumber")
        val emailId = req.getParameter("emailId")
        val country = req.getParameter("country")
        val user = User(id=id, fullName = fullName, mobileNumber = mobileNumber, emailId = emailId, country = country)
        if(userDAO.updateUser(user)) {
            res?.sendRedirect(req.contextPath + "/user-list")
        }else{
            //res.sendRedirect("/error.jsp")
            res.sendError(500, "Failed to update record")
        }
    }
}