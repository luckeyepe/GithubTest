package com.example.morkince.githubtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_mainHello.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Hello World")
            alertDialog.setPositiveButton("OK") { dialog, which -> dialog.dismiss() }

            alertDialog.show()
            //comment
        }

        btn_mainGoodbye.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("So long, farewell")
            alertDialog.setNegativeButton("NOOO") { dialog, which -> dialog.dismiss() }

            alertDialog.show()
            //commit
            //commit testeing

            //this is used to test the merging of code
        }
    }

    fun makeChange(){
        //test merging
        Log.d("Test", "Hello there")
        //new test for github
    }

    fun mickeyFunction(){
        Log.d("Mickey", "Mickey Logs")
    }
}
