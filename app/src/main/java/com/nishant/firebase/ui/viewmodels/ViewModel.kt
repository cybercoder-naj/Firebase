package com.nishant.firebase.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nishant.firebase.repository.Repository

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    fun signInAuth(email: String, password: String) =
        repository.signIntoFirebase(email, password)

    fun signOutAuth() =
        repository.signOutFirebase()

    fun closeAuth() = repository.closeFirebaseAuth()

}