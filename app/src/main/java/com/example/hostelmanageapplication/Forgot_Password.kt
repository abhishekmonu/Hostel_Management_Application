package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_forgot_password.*

class Forgot_Password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        var mobno = findViewById<EditText>(R.id.forgotPassword_mobno)
        var enrollno = findViewById<EditText>(R.id.forgotPassword_enrollno)

        forgotpassword_backarrow.setOnClickListener({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

        Nextstep.setOnClickListener({

            if(mobno.text.toString().isEmpty() || enrollno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill The All Details",Toast.LENGTH_SHORT).show()
            }
            else {

                var database = FirebaseDatabase.getInstance().getReference(enrollno.text.toString())
                database.get().addOnSuccessListener {
                    if(it.exists())
                    {
                        val mobileno = it.child("mobileno").value.toString()
                        val enrollmentno = it.child("enrollment").value.toString()
                        if(mobileno.equals(mobno.text.toString()) && enrollmentno.equals(enrollno.text.toString()))
                        {
                            val intent = Intent(this, Forgot_OTP::class.java)
                            intent.putExtra("mobileno", mobileno)
                            intent.putExtra("enrollno", enrollmentno)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this,"Mobile No Doest not match with this Enroll No",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Account Does not Exists",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })

    }
}