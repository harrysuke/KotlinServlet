package com.example.dao

//import com.example.database.Database
import com.example.database.DatabasePool
import com.example.model.UserAccount
import org.mindrot.jbcrypt.BCrypt

class UserAccountDAO {

    // Register new user – hash password before storing
    fun registerUser(user: UserAccount): Boolean {
        //val hashed = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val sql = "INSERT INTO tblusers (FullName, MobileNumber, country, EmailId, Username, Password, RegDate) VALUES (?,?,?,?,?,?,?)"
        val hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(user.password, org.mindrot.jbcrypt.BCrypt.gensalt())
        DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.fullName)
                stmt.setString(2, user.mobileNumber)
                stmt.setString(3, user.emailId)
                stmt.setString(4, user.country)
                stmt.setString(5, user.username)
                stmt.setString(6, hashedPassword)
                stmt.setString(7, user.regDate)
                return stmt.executeUpdate() > 0
            }
        }
    }

    // Validate login credentials
    fun validateLogin(username: String, plainPassword: String): UserAccount? {
        val sql = "SELECT * FROM tblusers WHERE Username=?"
        DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, username)
                val rs = pstmt.executeQuery()
                if (rs.next()) {
                    val storedHash = rs.getString("password")
                    if (BCrypt.checkpw(plainPassword, storedHash)) {
                        return UserAccount(
                            fullName = rs.getString("fullName"),
                            mobileNumber  = rs.getString("mobileNumber"),
                            country = rs.getString("country"),
                            emailId  = rs.getString("emailId"),
                            username = rs.getString("username"),
                            password = storedHash,
                            regDate = rs.getString("regDate")
                        )
                    }
                }
            }
        }
        return null
    }

    // Optional: check if username or email already exists
    fun isUsernameTaken(username: String): Boolean {
        val sql = "SELECT id FROM tblusers WHERE EmailId=?"
        return DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, username)
                pstmt.executeQuery().next()
            }
        }
    }

    fun isEmailTaken(email: String): Boolean {
        val sql = "SELECT id FROM tblusers WHERE EmailId=?"
        return DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, email)
                pstmt.executeQuery().next()
            }
        }
    }
}