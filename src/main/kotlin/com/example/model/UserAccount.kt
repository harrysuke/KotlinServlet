package com.example.model

data class UserAccount(
    val fullName: String,
    val mobileNumber: String,
    val emailId: String,
    val country: String,
    val username: String,
    val password: String,
    val regDate: String? = null
)