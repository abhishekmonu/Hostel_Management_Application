package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_forgot_new_password.*

class Forgot_NewPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_new_password)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        var newpassword = findViewById<EditText>(R.id.Pass_newPassword)
        var confirmpassword = findViewById<EditText>(R.id.Pass_confirmPassword)

        newpass_submit.setOnClickListener({

            if(newpassword.text.toString().isEmpty() || confirmpassword.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill the details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (newpassword.text.toString().equals(confirmpassword.text.toString())) {
                    var database = FirebaseDatabase.getInstance().reference
                    database.child(enrollment).child("password").setValue(newpassword.text.toString())
                    database.child(enrollment).child("confirmpassword").setValue(confirmpassword.text.toString())
                    Toast.makeText(this, "New Password Update Successfully", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "Try Login After 2 min", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "New Password & Confirm Password Does not Match", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}