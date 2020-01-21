package com.nishant.firebase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.nishant.firebase.R
import com.nishant.firebase.ui.viewmodels.ViewModel
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var model: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        model = ViewModelProviders.of(this)[ViewModel::class.java]

        btn_sign_up.setOnClickListener {
            val email = signup_email.text.toString()
            val pass = signup_password.text.toString()
            val confirmPass = confirm_password.text.toString()
            if (!TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(pass) &&
                !TextUtils.isEmpty(confirmPass)
            ) {
                if (pass != confirmPass) {
                    Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show()
                } else {
                    model.signUpAuth(email, pass) {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Successfully signed in!", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(
                                Intent(
                                    this,
                                    DatabaseActivity::class.java
                                )
                            )
                        } else {
                            try {
                                throw it.exception!!
                            } catch (weakPassword: FirebaseAuthWeakPasswordException) {
                                Toast.makeText(this, "Password is too weak.", Toast.LENGTH_SHORT)
                                    .show()
                            } catch (invalidCredentials: FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(this, "Email is malformed.", Toast.LENGTH_SHORT)
                                    .show()
                            } catch (collision: FirebaseAuthUserCollisionException) {
                                Toast.makeText(
                                    this,
                                    "This email already exists.",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } catch (nullPointer: KotlinNullPointerException) {
                                Toast.makeText(this, "Problem signing in.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }
}