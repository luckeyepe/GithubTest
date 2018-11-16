package com.example.morkince.githubtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
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
                docRef.get().addOnCompleteListener {
                    task: Task<DocumentSnapshot> ->
                    if (task.isSuccessful) {
                        var document = task.result
                        if (document!!.exists()) {
                            popupError("Student is already in the database", "OK")
                        } else {
                            addStudent(firestore)
                        }
                    }else{
                        popupError("Error in the database", "OK")
                        Log.e("Error in Checking", task.exception.toString())
                    }
                }
            }else{
                popupError("Enter all data fields", "OK")
            }
        }
    }


    private fun addStudent(firestore: FirebaseFirestore) {
        Log.d("Promp", "Adding Student Start")

        var idNumber = edt_mickeyIDNumber.text.toString().trim().toInt()
        var firstName = edt_mickeyFirstName.text.toString().trim()
        var middleName = edt_mickeyMiddleName.text.toString().trim()
        var lastName = edt_mickeyLastName.text.toString().trim()
        var course = edt_mickeyCourse.text.toString().trim()
        var yearLevel = edt_mickeyYearLevel.text.toString().trim().toInt()

        var map = HashMap<String, Any>()
        map["idNumber"] = idNumber
        map["firstName"] = firstName
        map["middleName"] = middleName
        map["lastName"] = lastName
        map["course"] = course
        map["yearLevel"] = yearLevel

        firestore.collection("Students").document("$idNumber").set(map)
            .addOnSuccessListener {
                Log.d("Success", "Added Student to Firestore")
                popupSuccess("Student is now enrolled", "OK")
        }
            .addOnFailureListener {
                exception -> Log.e("Error", "Failed to add Student to Firestore")
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

    private fun isCompleteField(): Boolean{
        return (!edt_mickeyIDNumber.text.isNullOrEmpty() && !edt_mickeyFirstName.text.isNullOrEmpty() && !edt_mickeyMiddleName.text.isNullOrEmpty()
                && !edt_mickeyLastName.text.isNullOrEmpty() && !edt_mickeyCourse.text.isNullOrEmpty() && !edt_mickeyYearLevel.text.isNullOrEmpty())
    }
}
