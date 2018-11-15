package com.example.morkince.githubtest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.morkince.githubtest.R;

public class Titus_Activity extends AppCompatActivity {
Button alertDialog,cancelActivity,alertTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titus_);
        ref();
        alertDialog.setOnClickListener(alert);
        cancelActivity.setOnClickListener(cancelAct);
        alertTwo.setOnClickListener(alert2);
    }
    public View.OnClickListener alert = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Titus_Activity.this, "Japhet Titus Abanto", Toast.LENGTH_LONG).show();
        }
    };
    public View.OnClickListener alert2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(Titus_Activity.this,
                    MyActivityAbanto.class);
            startActivity(myIntent);
        }
    };
    public View.OnClickListener cancelAct = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        finish();
        }
    };
    public void ref()
    {
        alertDialog =findViewById(R.id.btn_titusAlert);
        alertTwo =findViewById(R.id.btn_titusAlert2);
        cancelActivity= findViewById(R.id.btn_titusCancel);

    }
}
