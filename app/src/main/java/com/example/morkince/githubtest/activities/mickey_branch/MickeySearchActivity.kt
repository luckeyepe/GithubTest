package com.example.morkince.githubtest.activities.mickey_branch

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.widget.TextView
import com.example.morkince.githubtest.R
import com.example.morkince.githubtest.R.string.*
import com.example.morkince.githubtest.models.Student
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_mickey_search.*

class MickeySearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mickey_search)
        var firestore = FirebaseFirestore.getInstance()

        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        firestore.firestoreSettings = settings

        btn_mickeySearchSearch.setOnClickListener {
            if (isNotEmptyIDNumberEditText()) {
                val idNumber = edt_mickeySearchIDNumber.text.toString().trim()

                //show progress dialog
                val builder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
                val message = dialogView.findViewById<TextView>(R.id.txt_progress_dialog)
                message.text = "Searching for Student"
                builder.setView(dialogView)
                builder.setCancelable(false)
                val dialog = builder.create()
                dialog.show()

                //search student
                searchStudent(firestore, idNumber)

                //dismiss progress dialog
                dialog.dismiss()
            }else{
                popupError("Enter ID Number first", "OK")
            }
        }

        btn_mickeySearchDelete.setOnClickListener {
            //check if a student's info in presented
            if (txt_searchMickeyIDNumber.text != getString(R.string.default_student_id_number)) {
                //show progress dialog
                val builder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
                val message = dialogView.findViewById<TextView>(R.id.txt_progress_dialog)
                message.text = "Searching for Student"
                builder.setView(dialogView)
                builder.setCancelable(false)
                val dialog = builder.create()
                dialog.show()

                //delete student
                val idNumber = txt_searchMickeyIDNumber.text.toString().trim()
                deleteStudent(firestore, idNumber)

                //dismiss progress dialog
                dialog.dismiss()
            }else{
                popupError("Enter ID Number first", "OK")
            }
        }
    }

    private fun deleteStudent(firestore: FirebaseFirestore, idNumber: String) {
        val docRef = firestore.collection("Students").document("$idNumber")
        docRef.delete()
            .addOnSuccessListener {
                clearStudentInfo()
                popupSuccess("Student has been removed", "OK")
                Log.d("Prompt:", "Student $idNumber is removed from database")
            }
            .addOnFailureListener {
                exception ->
                popupError("Delete Unsuccessful", "OK")
                Log.e("Error", "Delete Student Error", exception)
            }

    }

    private fun clearStudentInfo() {
        edt_mickeySearchIDNumber.text = null
        txt_searchMickeyIDNumber.text = getText(R.string.default_student_id_number)
        txt_mickeySearchFullName.text = getText(R.string.default_student_full_name)
        txt_mickeySearchCourseYear.text = getText(R.string.default_student_course_year)
    }

    private fun searchStudent(firestore: FirebaseFirestore,idNumber: String) {
        val docRef = firestore.collection("Students").document("$idNumber")

        docRef.get().addOnCompleteListener {
                task: Task<DocumentSnapshot> ->
            if (task.isSuccessful) {
                var document = task.result

                if (document!!.exists()) {
                    var student = document.toObject(Student::class.java)
                    val fullName = "${student!!.lastName}, ${student.firstName} ${student.middleName[0]}."
                    val courseYear = "${student.course} - ${student.yearLevel}"

                    txt_searchMickeyIDNumber.text = idNumber.toString()
                    txt_mickeySearchFullName.text = fullName
                    txt_mickeySearchCourseYear.text = courseYear

                } else {
                    //if id number is unique add student
                    popupError("Student is not in the database", "OK")
                    edt_mickeySearchIDNumber.requestFocus()
                }
            }else{
                //Error during pulling the data from fire store
                popupError("Error in the database", "OK")
                Log.e("Error in Checking", task.exception.toString())
            }
        }
    }

    private fun isNotEmptyIDNumberEditText(): Boolean {
        return !edt_mickeySearchIDNumber.text.isNullOrEmpty()
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
}

