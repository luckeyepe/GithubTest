package com.example.morkince.githubtest.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.morkince.githubtest.R
import com.example.morkince.githubtest.activities.mickey_branch.MickeyActivity
import com.example.morkince.githubtest.activities.mickey_branch.MickeyDashboardActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_mainMickey.setOnClickListener {
            startActivity(Intent(this, MickeyDashboardActivity::class.java))
        }

        btn_mainMarc.setOnClickListener {
            startActivity(Intent(this, MarcActivity::class.java))
        }

        btn_mainMikay.setOnClickListener {
            startActivity(Intent(this, MikayActivity::class.java))
        }

        btn_mainTitus.setOnClickListener {
            startActivity(Intent(this, Titus_Activity::class.java))
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
    fun titusFunction() {
        Log.d("Japhet", "TitusAbanto Log")
    }
    fun mikayFunction(){
        Log.d("Mkay", "MikayL Logs")
    }
    fun mama(){
        Log.d("Mickey", "Marc Logs")
    }
    fun marcFunction(){
        Log.d("Mickey", "Marc Logs")
    }

}
