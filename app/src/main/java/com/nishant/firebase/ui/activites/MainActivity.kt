package com.nishant.firebase.ui.activites

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.nishant.firebase.R
import com.nishant.firebase.ui.viewmodels.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model = ViewModelProviders.of(this)[ViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email_sign_in_button.setOnClickListener {
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()
            if (!TextUtils.isEmpty(emailStr) && !TextUtils.isEmpty(passwordStr))
                if (model.signInAuth(emailStr, passwordStr))
                    Toast.makeText(this, "Successfully signed in!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Problem signing in.", Toast.LENGTH_SHORT).show()
        }

        email_sign_out_button.setOnClickListener {
            model.signOutAuth()
            Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        model.closeAuth()
    }
}
