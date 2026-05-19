package com.example.model

data class User(
    var id: Int,
    var fullName: String,
    var mobileNumber: String,
    var emailId: String,
    var country: String,
    var regDate: String = ""
)
