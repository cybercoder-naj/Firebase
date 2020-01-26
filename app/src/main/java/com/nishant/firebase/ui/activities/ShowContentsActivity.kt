package com.nishant.firebase.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.nishant.firebase.R
import com.nishant.firebase.ui.viewmodels.ViewModel
import kotlinx.android.synthetic.main.activity_show_contents.*

class ShowContentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contents)

        val model = ViewModelProviders.of(this)[ViewModel::class.java]
        model.showData(list_view)
    }
}