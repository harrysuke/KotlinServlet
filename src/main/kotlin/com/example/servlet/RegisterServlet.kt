package com.example.servlet

import com.example.dao.UserAccountDAO
import com.example.model.UserAccount
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/register")
class RegisterServlet : HttpServlet(){
    private val userDAO = UserAccountDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse){
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res)
    }
    override fun doPost(req: HttpServletRequest, res: HttpServletResponse){
        val fullName = req.getParameter("fullName")
        val mobileNumber = req.getParameter("mobileNumber")
        val emailId = req.getParameter("emailId")
        val country = req.getParameter("country")
        val username = req.getParameter("username")
        val password = req.getParameter("password")
        val confirmPassword = req.getParameter("confirmPassword")
        val regDate = java.time.LocalDate.now().toString()

        if(password != confirmPassword){
            req.setAttribute("error","Password do not match")
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res)
            return
        }
        if(userDAO.isUsernameTaken(username)){
            req.setAttribute("error","Username already taken")
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res)
            return
        }
        
        if (password == confirmPassword) {
            val user = UserAccount(fullName, mobileNumber, emailId, country, username, password, regDate)
            if (userDAO.registerUser(user)) {
                res.sendRedirect(req.contextPath + "/")
            } else {
                req.setAttribute("error", "Registration failed.")
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res)
            }
        } else {
            req.setAttribute("error", "Passwords do not match.")
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res)
        }
    }


}