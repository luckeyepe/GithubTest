package com.example.morkince.githubtest.activities.mickey_branch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.TextView
import com.example.morkince.githubtest.R
import kotlinx.android.synthetic.main.activity_mickey.*
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*


class MickeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mickey)

        btn_mickeyAdd.setOnClickListener {
            if (isCompleteField()){
                var idNumber = edt_mickeyIDNumber.text.toString().trim().toInt()
                val firestore = FirebaseFirestore.getInstance()
                val settings = FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build()
                firestore.firestoreSettings = settings

                val docRef = firestore.collection("Students").document("$idNumber")

                //show progress dialog
                val builder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
                val message = dialogView.findViewById<TextView>(R.id.txt_progress_dialog)
                message.text = "Checking if student ID number is unique"
                builder.setView(dialogView)
                builder.setCancelable(false)
                val dialog = builder.create()
                dialog.show()

                //check if id number is unique
                docRef.get().addOnCompleteListener {
                    task: Task<DocumentSnapshot> ->
                    if (task.isSuccessful) {
                        var document = task.result
                        if (document!!.exists()) {
                            //dismiss progress dialog
                            dialog.dismiss()

                            popupError("Student is already in the database", "OK")
                            Log.d("Student Data:", "${document.data}")
                        } else {
                            //dismiss progress dialog
                            dialog.dismiss()

                            //if id number is unique add student
                            addStudent(firestore)
                        }
                    }else{
                        //dismiss progress dialog
                        dialog.dismiss()

                        //Error during pulling the data from fire store
                        popupError("Error in the database", "OK")
                        Log.e("Error in Checking", task.exception.toString())
                    }
                }

                //dismiss progress dialog
                dialog.dismiss()
            }else{
                popupError("Enter all data fields", "OK")
            }
        }
    }


    //This method recieves an instance of Firestore, and adds a student into the "Students" collection
    private fun addStudent(firestore: FirebaseFirestore) {
        Log.d("Prompt:", "Adding Student Start")

        var idNumber = edt_mickeyIDNumber.text.toString().trim().toInt()
        var firstName = edt_mickeyFirstName.text.toString().toUpperCase().trim()
        var middleName = edt_mickeyMiddleName.text.toString().toUpperCase().trim()
        var lastName = edt_mickeyLastName.text.toString().toUpperCase().trim()
        var course = edt_mickeyCourse.text.toString().toUpperCase().trim()
        var yearLevel = edt_mickeyYearLevel.text.toString().trim().toInt()

        //show progress dialog
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.txt_progress_dialog)
        message.text = "Adding Student"
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        var map = HashMap<String, Any>()
        map["idNumber"] = idNumber
        map["firstName"] = firstName
        map["middleName"] = middleName
        map["lastName"] = lastName
        map["course"] = course
        map["yearLevel"] = yearLevel

        //write into firestore
        firestore.collection("Students").document("$idNumber").set(map)
            .addOnSuccessListener {
                //dismiss progress dialog
                dialog.dismiss()

                Log.d("Success", "Added Student to Firestore")
                popupSuccess("Student is now enrolled", "OK")
        }
            .addOnFailureListener {
                exception ->
                //dismiss progress dialog
                dialog.dismiss()

                Log.e("Error", "Failed to add Student to Firestore")
        }
    }

    private fun popupError(message: String, positveMessage: String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(positveMessage) { dialog, _ -> dialog.dismiss() }

        alertDialog.show()
    }

    private fun popupError() {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setMessage("Hello there my name is Mickey Mouse")
        alertDialog.setPositiveButton("YEEEAH") { dialog, _ -> dialog.dismiss() }

        alertDialog.show()
    }

    private fun popupSuccess(message: String, positveMessage: String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(positveMessage) { dialog, _ -> dialog.dismiss() }

        alertDialog.show()
    }

    //Checks if all the datafields have data
    private fun isCompleteField(): Boolean{
        return (!edt_mickeyIDNumber.text.isNullOrEmpty() && !edt_mickeyFirstName.text.isNullOrEmpty() && !edt_mickeyMiddleName.text.isNullOrEmpty()
                && !edt_mickeyLastName.text.isNullOrEmpty() && !edt_mickeyCourse.text.isNullOrEmpty() && !edt_mickeyYearLevel.text.isNullOrEmpty())
    }
}
