package com.nishant.firebase.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.nishant.firebase.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {}

        email_sign_in_button.setOnClickListener {
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()
            if(!TextUtils.isEmpty(emailStr) && !TextUtils.isEmpty(passwordStr))
                mAuth.signInWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Successfully logged in!!", Toast.LENGTH_SHORT).show()
                        }
                        else
                            Toast.makeText(this, "Incorrect ID or Password!", Toast.LENGTH_SHORT).show()
                    }
        }

        email_sign_out_button.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener!!)
    }
}
