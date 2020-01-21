package com.nishant.firebase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.nishant.firebase.R
import com.nishant.firebase.ui.viewmodels.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var model: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this)[ViewModel::class.java]

        email_sign_in_button.setOnClickListener {
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()
            if (!TextUtils.isEmpty(emailStr) && !TextUtils.isEmpty(passwordStr)) {
                Log.d(TAG, "SignIn Button OnClickListener() called.")
                model.signInAuth(emailStr, passwordStr) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successfully signed in!", Toast.LENGTH_SHORT).show()
                        startActivity(
                            Intent(
                                this,
                                DatabaseActivity::class.java
                            )
                        )
                    } else {
                        try {
                            throw it.exception!!
                        } catch (invalidEmail: FirebaseAuthInvalidUserException) {
                            Toast.makeText(this, "Email ID does not exist.", Toast.LENGTH_SHORT)
                                .show()
                        } catch (invalidCredentials: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "Email ID does not exist.", Toast.LENGTH_SHORT)
                                .show()
                        } catch (nullPointer: KotlinNullPointerException) {
                            Toast.makeText(this, "Problem signing in.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        btn_sign_up.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SignUpActivity::class.java
                )
            )
        }
    }

    override fun onStop() {
        super.onStop()
        model.closeAuth()
    }
}
