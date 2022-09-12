package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_change_password.*

class Change_Password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        var database = FirebaseDatabase.getInstance().reference

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        var currentpassword = findViewById<TextInputEditText>(R.id.student_menu_currentPassword)
        var newpassword = findViewById<TextInputEditText>(R.id.student_menu_newPassword)
        var confirmpassword = findViewById<TextInputEditText>(R.id.student_menu_confirmPassword)

        stu_save.setOnClickListener{

            if(currentpassword.text.toString().isEmpty() || newpassword.text.toString().isEmpty() || confirmpassword.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill the details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var database2 = FirebaseDatabase.getInstance().getReference(enrollment)
                database2.get().addOnSuccessListener {
                    if(it.exists()) {
                        val cpassword = it.child("password").value.toString()

                        if(cpassword.equals(currentpassword.text.toString()))
                        {
                            if(newpassword.text.toString().equals(confirmpassword.text.toString()))
                            {

                                database.child(enrollment).child("password").setValue(newpassword.text.toString())
                                database.child(enrollment).child("confirmpassword").setValue(confirmpassword.text.toString())
                                Toast.makeText(this,"New Password Update Successfully", Toast.LENGTH_SHORT).show()
                                var intent = Intent(this,Menu_Student::class.java)
                                intent.putExtra("enrollno",enrollment)
                                startActivity(intent)
                            }
                            else
                            {
                                Toast.makeText(this,"New Password & Confirm Password Does not Match", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else
                        {
                            Toast.makeText(this,"Current Password Not Matched",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Try Again Later",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        changepassword_backarrow.setOnClickListener({
            val intent = Intent(this,Menu_Student::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

    }
}