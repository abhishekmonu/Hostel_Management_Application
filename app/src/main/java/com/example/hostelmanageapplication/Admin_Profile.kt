package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class Admin_Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)

        var bundle: Bundle? =intent.extras
        var enrollment = bundle?.get("enrollno").toString()

        var a_name = findViewById<TextView>(R.id.admin_home_name)
        var a_email = findViewById<TextView>(R.id.admin_home_email)
        var a_mobno = findViewById<TextView>(R.id.admin_home_mobno)
        var a_bloodgroup = findViewById<TextView>(R.id.admin_home_bloodgroup)
        var a_dob = findViewById<TextView>(R.id.admin_home_dob)


        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_profile)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.noticeboard->{
                    val intent = Intent(this,admin_noticeboard::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.chat->{
                    val intent = Intent(this,admin_chat::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.timetable->{
                    val intent = Intent(this,admin_timetable::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.person->{
                    val intent = Intent(this,admin_menu::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }


        var database = FirebaseDatabase.getInstance().getReference(enrollment)
        database.get().addOnSuccessListener {
            if(it.exists())
            {
                val name = it.child("name").value
                val email = it.child("email").value
                val mobileno = it.child("mobileno").value
                val bloodgroup = it.child("bloodgroup").value
                val dateofbirth = it.child("dob").value

                a_name.text = name.toString()
                a_email.text = email.toString()
                a_mobno.text = mobileno.toString()
                a_bloodgroup.text = bloodgroup.toString()
                a_dob.text = dateofbirth.toString()
            }
        }
    }
}