package com.nishant.firebase.firebase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "UserAuthentication"

class UserAuthentication {
    internal var mAuth = FirebaseAuth.getInstance()
    private var mAuthListener = FirebaseAuth.AuthStateListener {
        val user = it.currentUser
        Log.d(
            TAG,
            if (user != null) "AuthStateChanged: Signed In; User = ${user.uid}"
            else "AuthStateChanged: Signed out;"
        )
    }

    init {
        mAuth.addAuthStateListener(mAuthListener)
    }

    fun signIn(userEmail: String, userPassword: String, callback: (Task<AuthResult>) -> Unit) {
        Log.d(TAG, "signIn() called; userEmail = $userEmail; userPassword = $userPassword")
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                callback.invoke(it)
            }
    }

    fun signUp(userEmail: String, userPassword: String, callback: (Task<AuthResult>) -> Unit) {
        Log.d(TAG, "signUp() called; userEmail = $userEmail; userPassword = $userPassword")
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                callback.invoke(it)
            }
    }
    fun close() {
        mAuth.removeAuthStateListener(mAuthListener)
    }

    fun signOut() = mAuth.signOut()
}