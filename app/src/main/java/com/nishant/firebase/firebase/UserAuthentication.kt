package com.nishant.firebase.firebase

import com.google.firebase.auth.FirebaseAuth

class UserAuthentication {
    private var mAuth = FirebaseAuth.getInstance()
    private var mAuthListener = FirebaseAuth.AuthStateListener {}

    init {
        mAuth.addAuthStateListener(mAuthListener)
    }

    fun signIn(userEmail: String, userPassword: String): Boolean {
        var successful = false
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                successful = it.isSuccessful
            }
        return successful
    }

    fun close() {
        mAuth.removeAuthStateListener(mAuthListener)
    }

    fun signOut()= mAuth.signOut()
}