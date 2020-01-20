package com.nishant.firebase.firebase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase

private const val TAG = "Database"
class Database {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference
    private val userAuthentication = UserAuthentication()

    fun <V> addData(data: Map<String, V>) {
        Log.d(TAG, "addData() called")
        val user = userAuthentication.mAuth.currentUser
        user?.let {
            val userUid = it.uid
            for (e in data) {
                Log.d(TAG, "$userUid to ${e.key} set ${e.value}")
                databaseReference.child(userUid)
                    .child(e.key)
                    .setValue(e.value)
            }
        }
    }
}