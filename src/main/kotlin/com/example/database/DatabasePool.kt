package com.example.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DatabasePool {
    private val dataSource by lazy {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/tms"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = "root"
            password = ""
            maximumPoolSize = 10
            minimumIdle = 2
            idleTimeout = 600000
            connectionTimeout = 30000
        }
        HikariDataSource(config)
    }

    fun getConnection() = dataSource.connection
}