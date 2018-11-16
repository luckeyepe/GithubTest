package com.example.morkince.githubtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.morkince.githubtest.R
import com.example.morkince.githubtest.models.Student
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_mickey.*
import com.google.firebase.firestore.FirebaseFirestoreSettings



class MickeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mickey)

        btn_mickeyAdd.setOnClickListener {
            if (isCompleteField()){
                addStudent()
            }else{
                popupError()
            }

        }
    }

    private fun addStudent() {
        Log.d("Promp", "Adding Student Start")
        var firestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        firestore.firestoreSettings = settings

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

//        var student = Student(firstName, middleName, lastName, course, yearLevel, idNumber)

        firestore.collection("Students").document("$idNumber").set(map)
            .addOnSuccessListener {
                Log.d("Success", "Added Student to Firestore")
                popupSuccess("Student is now enrolled", "OK")
        }
            .addOnFailureListener {
                exception -> Log.e("Error", "Failed to add Student to Firestore")
        }
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
