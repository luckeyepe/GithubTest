package com.example.morkince.githubtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.morkince.githubtest.R
import kotlinx.android.synthetic.main.activity_mickey.*

class MickeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mickey)

        btn_mickeyAdd.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Hello there my name is Mickey Mouse")
            alertDialog.setPositiveButton("YEEEAH") { dialog, _ -> dialog.dismiss() }

            alertDialog.show()
        }
    }
}
