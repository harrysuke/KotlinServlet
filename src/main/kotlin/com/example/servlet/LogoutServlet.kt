package com.example.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/logout")
class LogoutServlet : HttpServlet(){
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse){
        val session = req.getSession(false)
        session?.invalidate()
        res.sendRedirect("login")
    }
}