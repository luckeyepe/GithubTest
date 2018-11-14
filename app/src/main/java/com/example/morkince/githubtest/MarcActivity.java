package com.example.morkince.githubtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MarcActivity extends AppCompatActivity {
Button btn_marcAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marc);
        refs();
        btn_marcAlert.setOnClickListener(alertMarc);

    }

    private View.OnClickListener alertMarc = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                showAlert("MARC GWAPO!","ALERT!");
        }
    };

    public void showAlert(String Message,String label)
    {
        //set alert for executing the task
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(""+label);
        alert.setMessage(""+Message);

        alert.setPositiveButton("ALRIGHT!!", new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog, int id){
                dialog.cancel();
            }
        });

        Dialog dialog = alert.create();
        dialog.show();
    }

    public void refs(){
        btn_marcAlert=findViewById(R.id.btn_marcAlert);
    }
}
