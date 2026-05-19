package com.example.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DatabasePool {
    private val dataSource by lazy {
        // Read from environment variables or system properties
        var dbHost = System.getenv("DB_HOST") ?: "localhost"
        var dbPort = System.getenv("DB_PORT") ?: "3306"
        var dbName = System.getenv("DB_NAME") ?: "tms"
        var dbUser = System.getenv("DB_USER") ?: "root"
        var dbPassword = System.getenv("DB_PASSWORD") ?: ""

        var connectionString = "jdbc:mysql://$dbHost:$dbPort/$dbName?useSSL=false&serverTimezone=UTC"

        var config = HikariConfig().apply {
            this.jdbcUrl = connectionString
            this.username = dbUser
            this.password = dbPassword
            this.maximumPoolSize = 10
            this.minimumIdle = 2
            this.idleTimeout = 600000
            connectionTimeout = 30000
            driverClassName = "com.mysql.cj.jdbc.Driver"
        }
        HikariDataSource(config)
    }

    fun getConnection() = dataSource.connection
}