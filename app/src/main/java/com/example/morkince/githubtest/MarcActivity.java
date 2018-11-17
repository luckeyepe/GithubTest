package com.example.morkince.githubtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MarcActivity extends AppCompatActivity {
    Button btn_marcAdd,btn_marcSearch,btn_marcCancel,btn_marcUpdate,btn_marcDelete;
    EditText editTxt_marcIDNum, editTxt_marcfName,editTxt_marcMName,editTxt_marcLName,editTxt_marcCourse,editTxt_marcYearLevel;
    FirebaseFirestore db;
    Student stud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marc);
        refs();
        btn_marcAdd.setOnClickListener(addStudent);
        btn_marcSearch.setOnClickListener(searchStudent);
        btn_marcUpdate.setOnClickListener(updateStudent);
        btn_marcDelete.setOnClickListener(deleteStudent);

    }
    private View.OnClickListener deleteStudent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("Student").document("" + editTxt_marcIDNum.getText());
            docRef.delete().addOnSuccessListener(new OnSuccessListener < Void > () {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MarcActivity.this, "Data deleted !",
                            Toast.LENGTH_SHORT).show();
                }

            });

        }
    };

    private View.OnClickListener updateStudent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db = FirebaseFirestore.getInstance();
            DocumentReference studentToUpdate = db.collection("Student").document("" + editTxt_marcIDNum.getText());

            studentToUpdate.update("fName", "Moma");

            studentToUpdate.update("mName", "Dela Torre");

            studentToUpdate.update("lName", "Ortega");
            studentToUpdate.update("course", "BSCS");
            studentToUpdate.update("yearLevel", 4)
                    .addOnSuccessListener(new OnSuccessListener < Void > () {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MarcActivity.this, "Updated Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    };
    private View.OnClickListener searchStudent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db = FirebaseFirestore.getInstance();
            db.collection("Student").document("" + editTxt_marcIDNum.getText()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            Student student = document.toObject(Student.class);
                           /* Toast.makeText(MarcActivity.this, "" + student.getfName(),
                                    Toast.LENGTH_SHORT).show();*/
                            editTxt_marcIDNum.setText(student.getIdNum()+ "");
                            editTxt_marcfName.setText(student.getfName());
                            editTxt_marcMName.setText(student.getmName());
                            editTxt_marcLName.setText(student.getlName());
                            editTxt_marcCourse.setText(student.getCourse());
                            editTxt_marcYearLevel.setText(student.getYearLevel() + "");
                        }
                        else
                        {
                            Toast.makeText(MarcActivity.this, "STUDENT NOT FOUND!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    };

    private View.OnClickListener addStudent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
// Create a new user with a first and last name
        /*    Map<String, Object> user = new HashMap<>();
            user.put("first", "Ada");
            user.put("last", "Lovelace");
            user.put("born", 1815);*/
            db = FirebaseFirestore.getInstance();
            stud= new Student();

            stud.setIdNum(Integer.parseInt(editTxt_marcIDNum.getText().toString()));
            stud.setfName(editTxt_marcfName.getText().toString());
            stud.setmName(editTxt_marcMName.getText().toString());
            stud.setlName(editTxt_marcLName.getText().toString());
            stud.setCourse(editTxt_marcCourse.getText().toString());
            stud.setYearLevel(Integer.parseInt(editTxt_marcYearLevel.getText().toString()));


           db.collection("Student").document("" + editTxt_marcIDNum.getText()).set(stud)
                    .addOnSuccessListener(new OnSuccessListener < Void > () {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MarcActivity.this, "User Registered",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MarcActivity.this, "ERROR" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("TAG", e.toString());
                        }
                    });
// Add a new document with a generated ID
      /*     db.collection("Student")
                    .add(stud)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(MarcActivity.this, "User Registered",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                            Toast.makeText(MarcActivity.this, "User Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });*/

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

        btn_marcAdd=findViewById(R.id.btn_marcAdd);
        btn_marcSearch=findViewById(R.id.btn_marcSearch);
        btn_marcCancel=findViewById(R.id.btn_marcCancel);
        btn_marcUpdate=findViewById(R.id.btn_marcUpdate);
        btn_marcDelete=findViewById(R.id.btn_marcDelete);
        editTxt_marcIDNum=findViewById(R.id.editTxt_marcIDNum);
        editTxt_marcfName=findViewById(R.id.editTxt_marcfName);
        editTxt_marcMName=findViewById(R.id.editTxt_marcMName);
        editTxt_marcLName=findViewById(R.id.editTxt_marcLName);
        editTxt_marcCourse=findViewById(R.id.editTxt_marcCourse);
        editTxt_marcYearLevel=findViewById(R.id.editTxt_marcYearLevel);

    }
}
