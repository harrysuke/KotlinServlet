package com.example.model

class User {
    var id: Int = 0
    var fullName: String
    var mobileNumber: String
    var emailId: String
    var country: String

    constructor(id: Int, fullName: String, mobileNumber: String, emailId: String, country: String) {
        this.id = id
        this.fullName = fullName
        this.mobileNumber = mobileNumber
        this.emailId = emailId
        this.country = country
    }


}