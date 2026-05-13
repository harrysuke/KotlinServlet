package com.example.servlet.rest

import com.example.util.ApiResponse
import com.example.util.JsonUtil
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

abstract class BaseApiServlet : HttpServlet() {

    protected fun isAuthenticated(req: HttpServletRequest): Boolean {
        val session = req.getSession(false)
        return session != null && session.getAttribute("loggedUser") != null
    }

    protected fun sendJson(res: HttpServletResponse, statusCode: Int, response: ApiResponse) {
        res.contentType = "application/json"
        res.status = statusCode
        res.writer.write(JsonUtil.toJson(response))
    }

    protected fun sendSuccess(res: HttpServletResponse, data: Any? = null, message: String? = null) {
        sendJson(res, 200, ApiResponse(success = true, message = message, data = data))
    }

    protected fun sendError(res: HttpServletResponse, statusCode: Int, message: String) {
        sendJson(res, statusCode, ApiResponse(success = false, message = message))
    }

    protected fun getRequestBody(req: HttpServletRequest): String {
        return req.reader.readText()
    }
}