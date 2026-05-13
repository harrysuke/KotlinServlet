package com.example.servlet

import com.example.dao.UserAccountDAO
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession

@WebServlet("/login")
class LoginServlet : HttpServlet(){
    private val userDAO = UserAccountDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse){
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,res)
    }
    override fun doPost(req: HttpServletRequest, res: HttpServletResponse){
        val username = req.getParameter("username")
        val password = req.getParameter("password")
        val user = userDAO.validateLogin(username, password)

        if(user != null){
            val session: HttpSession = req.getSession(true)
            session.setAttribute("loggedUser", user.username)
            session.setAttribute("userId", user.password)
            res.sendRedirect(req.contextPath + "/")
        }else{
            req.setAttribute("error","invalid username or password")
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,res)
        }
    }
}