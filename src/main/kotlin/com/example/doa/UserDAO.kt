package com.example.doa

//import com.example.database.Database
import com.example.database.DatabasePool
import com.example.model.User
import java.sql.SQLException
//optimization
import com.github.benmanes.caffeine.cache.Caffeine
import java.util.concurrent.TimeUnit

class CachedUserDAO(private val delegate: UserDAO) {
    // 1. Remove the nullable '?' from User
    private val userCache = Caffeine.newBuilder()
        .expireAfterWrite(5, TimeUnit.MINUTES)
        .maximumSize(100)
        .build<Int, User>() 

    fun getUserById(id: Int): User? {
        // 2. Fetch from cache or load from DB if missing
        return userCache.get(id) { key -> 
            delegate.getUserById(key) 
        }
    }

    fun invalidate(id: Int) = userCache.invalidate(id)
    fun invalidateAll() = userCache.invalidateAll()
}

class UserDAO {
    fun insertUser(user: User): Boolean {
        val sql = "insert into tblusers (FullName, MobileNumber, EmailId, country) values (?, ?, ?, ?)"
        return try {
          DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, user.fullName)
                pstmt.setString(2, user.mobileNumber)
                pstmt.setString(3, user.emailId)
                pstmt.setString(4, user.country)
                val rowsAffected = pstmt.executeUpdate()
                rowsAffected > 0 
            }
          }
        }
        catch(e: Exception) {
            e.printStackTrace()
            false
        } 
    }
    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val sql = "select * from tblusers"
        DatabasePool.getConnection().use { conn ->
            conn.createStatement().use { stmt ->
                val rs = stmt.executeQuery(sql)
                while (rs.next()) {
                    users.add(
                        User(
                            id = rs.getInt("id"),
                            fullName = rs.getString("fullName"),
                            mobileNumber = rs.getString("mobileNumber"),
                            emailId = rs.getString("emailId"),
                            country = rs.getString("country")
                        )
                    )
                }
            }
        }
        return users
    }
    fun getUserById(id: Int): User? {
        val sql = "select * from tblusers where id=?"
        DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                val rs = pstmt.executeQuery()
                if(rs.next()){
                    return User(
                        id = rs.getInt("id"),
                        fullName = rs.getString("fullName"),
                        mobileNumber = rs.getString("mobileNumber"),
                        emailId = rs.getString("emailId"),
                        country = rs.getString("country")
                    )
                }
            }
        }
        return null
    }
    fun updateUser(user: User): Boolean {
        val sql = "update tblusers set fullName = ?, mobileNumber=?, emailId=?, country=? where id = ?"
        return DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, user.fullName)
                pstmt.setString(2, user.mobileNumber)
                pstmt.setString(3, user.emailId)
                pstmt.setString(4, user.country)
                pstmt.setInt(5, user.id)

                // executeUpdate returns the number of rows affected
                val affectedRows = pstmt.executeUpdate()
                affectedRows > 0 // Returns true if at least one row was updated
            }
        }
    }
    fun deleteUser(id: Int): Boolean {
        val sql = "delete from tblusers where id=?"
        return DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                // executeUpdate returns the count of deleted rows
                pstmt.executeUpdate() > 0
            }
        }
    }

}