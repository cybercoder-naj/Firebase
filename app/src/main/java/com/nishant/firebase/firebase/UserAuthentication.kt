package com.nishant.firebase.firebase

import android.util.Log
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

    fun signIn(userEmail: String, userPassword: String): Boolean {
        Log.d(TAG, "signIn() called; userEmail = $userEmail; userPassword = $userPassword")
        var successful = false
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                successful = it.isSuccessful
                Log.d(TAG, "successful = $successful")
            }
        Log.d(TAG, "successful = $successful")
        return successful
    }

    fun close() {
        mAuth.removeAuthStateListener(mAuthListener)
    }

    fun signOut() = mAuth.signOut()
}