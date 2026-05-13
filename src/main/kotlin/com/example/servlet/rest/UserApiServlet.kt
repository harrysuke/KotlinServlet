package com.example.servlet.rest

import com.example.doa.UserDAO
import com.example.model.User
import com.example.util.JsonUtil
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/api/users/*")
class UserApiServlet : BaseApiServlet() {

    private val userDAO = UserDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        //if (!isAuthenticated(req)) {
            //sendError(res, 401, "Unauthorized")
            //return
        //}

        val pathInfo = req.pathInfo
        when {
            pathInfo == null || pathInfo == "/" -> {
                // GET all users
                val users = userDAO.getAllUsers()
                sendSuccess(res, users)
            }
            pathInfo.matches(Regex("/\\d+")) -> {
                // GET single user
                val id = pathInfo.substring(1).toInt()
                val user = userDAO.getUserById(id)
                if (user != null) sendSuccess(res, user)
                else sendError(res, 404, "User not found")
            }
            else -> sendError(res, 404, "Not found")
        }
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        //if (!isAuthenticated(req)) {
            //sendError(res, 401, "Unauthorized")
            //return
        //}

        val body = getRequestBody(req)
        val user = JsonUtil.fromJson<User>(body)
        val success = userDAO.insertUser(user)
        if (success) {
            sendSuccess(res, message = "User created")
        } else {
            sendError(res, 500, "Failed to create user")
        }
    }

    override fun doPut(req: HttpServletRequest, res: HttpServletResponse) {
        //if (!isAuthenticated(req)) {
            //sendError(res, 401, "Unauthorized")
            //return
        //}

        val pathInfo = req.pathInfo
        if (pathInfo == null || !pathInfo.matches(Regex("/\\d+"))) {
            sendError(res, 400, "Invalid user ID")
            return
        }
        val id = pathInfo.substring(1).toInt()
        val body = getRequestBody(req)
        val user = JsonUtil.fromJson<User>(body).copy(id = id)
        val success = userDAO.updateUser(user)
        if (success) {
            sendSuccess(res, message = "User updated")
        } else {
            sendError(res, 500, "Failed to update user")
        }
    }

    override fun doDelete(req: HttpServletRequest, res: HttpServletResponse) {
        //if (!isAuthenticated(req)) {
            //sendError(res, 401, "Unauthorized")
            //return
        //}

        val pathInfo = req.pathInfo
        if (pathInfo == null || !pathInfo.matches(Regex("/\\d+"))) {
            sendError(res, 400, "Invalid user ID")
            return
        }
        val id = pathInfo.substring(1).toInt()
        val success = userDAO.deleteUser(id)
        if (success) {
            sendSuccess(res, message = "User deleted")
        } else {
            sendError(res, 500, "Failed to delete user")
        }
    }
}