package com.example.database

import java.sql.Connection
import java.sql.DriverManager

object Database{
    private const val URL = "jdbc:mysql://localhost:3306/tms"
    private const val USERNAME = "root"
    private const val PASSWORD = ""

    fun getConnection(): Connection {
        Class.forName("com.mysql.cj.jdbc.Driver")
        return DriverManager.getConnection(URL, USERNAME, PASSWORD)
    }
}