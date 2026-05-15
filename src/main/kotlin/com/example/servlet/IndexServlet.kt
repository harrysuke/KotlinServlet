package com.example.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/") // This defines your clean URL extension-free path
class IndexServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, res)
    }
}

@WebServlet("/api")
class ApiIndexServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        req.getRequestDispatcher("/WEB-INF/views/api.jsp").forward(req, res)
    }
}

@WebServlet("/search-form")
class SearchFormServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        req.getRequestDispatcher("/WEB-INF/views/searchForm.jsp").forward(req, res)
    }
}
