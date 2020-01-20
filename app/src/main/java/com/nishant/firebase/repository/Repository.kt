package com.nishant.firebase.repository

import com.nishant.firebase.firebase.Database
import com.nishant.firebase.firebase.UserAuthentication

class Repository {
    private val userAuthentication = UserAuthentication()
    private val database = Database()

    fun signIntoFirebase(email: String, password: String) =
        userAuthentication.signIn(email, password)

    fun signOutFirebase() = userAuthentication.signOut()

    fun closeFirebaseAuth() = userAuthentication.close()

    fun <V> addToFirebaseDatabase(map: Map<String, V>) = database.addData(map)
}