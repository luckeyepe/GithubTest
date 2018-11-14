package com.example.morkince.githubtest

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_mikay.*


class MikayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mikay)
        btn_mikayactivity2.setOnClickListener {
            val intent = Intent(this, com.example.morkince.githubtest.mikayactivity2::class.java)
            // start your next activity
            startActivity(intent)
        }
btn_Mikay.setOnClickListener{
    var alertDialog = AlertDialog.Builder(this)
    alertDialog.setCancelable(false)
    alertDialog.setMessage("Hello there my name is Michael angelo")
    alertDialog.setPositiveButton("YEEEAH", DialogInterface.OnClickListener { dialog, which ->

    })
    alertDialog.show()
}


    }
}


