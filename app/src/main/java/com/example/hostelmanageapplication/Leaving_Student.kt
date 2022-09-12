package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_leaving_student.*

class Leaving_Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaving_student)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        var date = findViewById<TextInputEditText>(R.id.student_leaving_date)
        var address = findViewById<TextInputEditText>(R.id.student_leaving_address)
        var time = findViewById<TextInputEditText>(R.id.student_leaving_time)
        var purpose = findViewById<TextInputEditText>(R.id.student_leaving_purpose)
        var return_date = findViewById<TextInputEditText>(R.id.student_leaving_return_date)

        student_leaving_Submit.setOnClickListener({

            if(date.text.toString().isEmpty() || address.text.toString().isEmpty() || time.text.toString().isEmpty() || purpose.text.toString().isEmpty() || return_date.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill All Details",Toast.LENGTH_SHORT).show()
            }
            else {

                val database = FirebaseDatabase.getInstance().getReference(enrollment)
                database.get().addOnSuccessListener {
                    if (it.exists()) {
                        val name = it.child("name").value
                        val enrollment = it.child("enrollment").value
                        val semester = it.child("semester").value
                        val college = it.child("college").value
                        val hostelname = it.child("hostelname").value
                        val roomno = it.child("roomno").value

                        val database2 = FirebaseDatabase.getInstance().getReference("Leaving_Hostel")
                        database2.child(enrollment.toString()).setValue(
                            Leaving_Model(
                                enrollment.toString(),
                                name.toString(),
                                semester.toString(),
                                college.toString(),
                                roomno.toString(),
                                hostelname.toString(),
                                date.text.toString(),
                                purpose.text.toString(),
                                address.text.toString(),
                                return_date.text.toString(),
                                time.text.toString()
                            )
                        )
                        Toast.makeText(this,"Submit Successfully", Toast.LENGTH_SHORT).show()
                        student_leaving_date.setText("")
                        student_leaving_address.setText("")
                        student_leaving_time.setText("")
                        student_leaving_purpose.setText("")
                        student_leaving_return_date.setText("")
                    }
                }
            }

        })
        leaving_student_backarrow.setOnClickListener({
            val intent = Intent(this,Menu_Student::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

    }
}