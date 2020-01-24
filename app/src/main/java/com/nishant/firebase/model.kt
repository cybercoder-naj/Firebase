package com.nishant.firebase

data class StudentInformation(var name: String, var age: Int) {
    constructor(): this("", 0)
}