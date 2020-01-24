package com.nishant.firebase.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nishant.firebase.StudentInformation

private const val TAG = "Database"
class Database {
    private val firebaseDatabase = Firebase.database
    private val databaseReference = firebaseDatabase.reference
    private val userAuthentication = UserAuthentication()

    init {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val student = StudentInformation()
                    val studentUid = userAuthentication.mAuth.currentUser?.uid ?: ""
                    student.name = ds.child(studentUid).getValue(StudentInformation::class.java)?.name ?: ""
                    student.age = ds.child(studentUid).getValue(StudentInformation::class.java)?.age ?: 0

                }
            }
        })
    }

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