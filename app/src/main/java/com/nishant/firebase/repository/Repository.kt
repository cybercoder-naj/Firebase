package com.nishant.firebase.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.nishant.firebase.firebase.Database
import com.nishant.firebase.firebase.UserAuthentication

class Repository {
    private val userAuthentication = UserAuthentication()
    private val database = Database()

    fun signIntoFirebase(email: String, password: String, callback: (Task<AuthResult>) -> Unit) =
        userAuthentication.signIn(email, password, callback)

    fun signOutFirebase() = userAuthentication.signOut()

    fun closeFirebaseAuth() = userAuthentication.close()

    fun <V> addToFirebaseDatabase(map: Map<String, V>) = database.addData(map)

    fun signUpFirebase(email: String, password: String, callback: (Task<AuthResult>) -> Unit) =
        userAuthentication.signUp(email, password, callback)
}