package com.example.servlet

import com.example.doa.UserDAO // 1. Ensure this package matches your project
import com.example.model.User
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/user-insert")
class CreateServlet : HttpServlet() {
    // 2. Changed to UserDAO (matching your previous class name)
    private val userDAO = UserDAO()

    override fun doPost(req: HttpServletRequest?, res: HttpServletResponse?) {
        val fullName = req?.getParameter("fullName") ?: ""
        val mobileNumber = req?.getParameter("mobileNumber") ?: ""
        val emailId = req?.getParameter("emailId") ?: ""
        val country = req?.getParameter("country") ?: ""

        val user = User(id = 0, fullName = fullName, mobileNumber = mobileNumber, emailId = emailId, country = country)

        // 3. Fixed casing to match the variable above
        if (userDAO.insertUser(user)) {
            res?.sendRedirect("${req?.contextPath}/user-list")
        } else {
            res?.sendError(500)
        }
    }
}
