package com.nishant.firebase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.nishant.firebase.R
import com.nishant.firebase.ui.viewmodels.ViewModel
import kotlinx.android.synthetic.main.activity_database.*

private const val TAG = "DatabaseActivity"
class DatabaseActivity : AppCompatActivity() {

    private lateinit var model: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        model = ViewModelProviders.of(this)[ViewModel::class.java]

        btn_add.setOnClickListener {
            val fieldName = txt_field_name.text.toString()
            val fieldValue = txt_field_value.text.toString()
            if (!TextUtils.isEmpty(fieldName) && !TextUtils.isEmpty(fieldValue)) {
                Log.d(TAG, "Adding to DB")
                model.addData(
                    mapOf(
                        fieldName to fieldValue
                    )
                )
                Toast.makeText(this, "Added to database!", Toast.LENGTH_SHORT).show()
            }
        }

        btn_view_contents.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ShowContentsActivity::class.java
                )
            )
        }

        btn_sign_out.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Signing out!", Toast.LENGTH_SHORT).show()
        model.signOutAuth()
    }
}