package com.nishant.firebase.repository

import com.nishant.firebase.firebase.UserAuthentication

class Repository {
    private val userAuthentication = UserAuthentication()

    fun signIntoFirebase(email: String, password: String) =
        userAuthentication.signIn(email, password)

    fun signOutFirebase() = userAuthentication.signOut()

    fun closeFirebaseAuth() = userAuthentication.close()
}