package com.example.dao

//import com.example.database.Database
import com.example.database.DatabasePool
import com.example.model.UserAccount
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate

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
                stmt.setDate(7, user.regDate?.let{java.sql.Date.valueOf(it)})
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
                            regDate = rs.getDate("regDate")?.toLocalDate()
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

    // 1. Added explicit return type ': MutableList<UserAccount>' to fix line 98
    fun getUsersByDateRange(startDate: LocalDate, endDate: LocalDate): MutableList<UserAccount> {
        val users = mutableListOf<UserAccount>()
        val sql = "SELECT * FROM tblusers WHERE RegDate BETWEEN ? AND ?"
        DatabasePool.getConnection().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                // 2. Added '.toString()' to fix lines 82 and 83
                pstmt.setString(1, startDate.toString())
                pstmt.setString(2, endDate.toString())
                val rs = pstmt.executeQuery()
                while (rs.next()) {
                users.add(UserAccount(
                    fullName = rs.getString("fullName"),
                    mobileNumber  = rs.getString("mobileNumber"),
                    country = rs.getString("country"),
                    emailId  = rs.getString("emailId"),
                    username = rs.getString("username"),
                    password = rs.getString("password"),
                    // 3. Added '.toLocalDate()' to fix line 93 (handles null safely)
                    regDate = rs.getDate("regDate")?.toLocalDate()
                    ))
                }
            }
        }
        return users
    }


}