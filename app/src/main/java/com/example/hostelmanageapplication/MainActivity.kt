package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var username = findViewById<EditText>(R.id.login_username)
        var password = findViewById<EditText>(R.id.login_password)

        S_Register.setOnClickListener({
            var intent = Intent(this,activiity_student_registration::class.java)
            startActivity(intent)
        })

        Login.setOnClickListener({

            if(username.text.toString().isEmpty() || password.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill All Details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var database = FirebaseDatabase.getInstance().getReference(username.text.toString())
                database.get().addOnSuccessListener {
                    if (it.exists())
                    {
                        val enrollment = it.child("enrollment").value.toString()
                        val password2 = it.child("password").value.toString()
                        val mobilenumber = it.child("mobileno").value.toString()
                        val rolle = it.child("role").value.toString()

                        if (enrollment.equals(username.text.toString()) && password2.equals(password.text.toString()))
                        {
                            var intent = Intent(this,OTP_Login::class.java)
                            intent.putExtra("mobileno",mobilenumber)
                            intent.putExtra("enrollno",enrollment)
                            intent.putExtra("role",rolle)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this,"Password Does Not Match",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Account Does Not Exists",Toast.LENGTH_SHORT).show()
                    }
                }
                database.get().addOnFailureListener({
                    Toast.makeText(this,"Username and Password Does Not Exists",Toast.LENGTH_SHORT).show()
                })
            }

        })

        login_forgot_password.setOnClickListener({
            val intent = Intent(this,Forgot_Password::class.java)
            startActivity(intent)
        })


    }
}