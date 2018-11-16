package com.example.morkince.githubtest.activities.mickey_branch

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.morkince.githubtest.R
import kotlinx.android.synthetic.main.activity_mickey_dashboard.*

class MickeyDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mickey_dashboard)

        btn_mickeyDashboardAddStudent.setOnClickListener {
            startActivity(Intent(this, MickeyActivity::class.java))
        }

        btn_mickeyDashboardSearchUpdateDelete.setOnClickListener {
            startActivity(Intent(this, MickeySearchActivity::class.java))
        }
    }
}
