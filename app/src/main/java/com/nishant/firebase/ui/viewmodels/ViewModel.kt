package com.nishant.firebase.ui.viewmodels

import android.app.Application
import android.widget.ListView
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.nishant.firebase.repository.Repository

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    fun signInAuth(email: String, password: String, callback: (Task<AuthResult>) -> Unit) =
        repository.signIntoFirebase(email, password, callback)

    fun signUpAuth(email: String, password: String, callback: (Task<AuthResult>) -> Unit) =
        repository.signUpFirebase(email, password, callback)

    fun signOutAuth() =
        repository.signOutFirebase()

    fun closeAuth() = repository.closeFirebaseAuth()

    fun <V> addData(map: Map<String, V>) = repository.addToFirebaseDatabase(map)

    fun showData(listView: ListView) = repository.showContentFirebaseDatabase(listView)
}