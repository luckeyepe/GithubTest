package com.example.morkince.githubtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Titus_Activity extends AppCompatActivity {
Button alertDialog,cancelActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titus_);
        ref();
        alertDialog.setOnClickListener(alert);
        cancelActivity.setOnClickListener(cancelAct);
    }
    public View.OnClickListener alert = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Titus_Activity.this, "Japhet Titus Abanto", Toast.LENGTH_LONG).show();
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
        cancelActivity= findViewById(R.id.btn_titusCancel);
    }
}
