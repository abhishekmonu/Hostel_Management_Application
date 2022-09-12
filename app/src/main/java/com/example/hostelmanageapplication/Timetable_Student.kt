package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Timetable_Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_student)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_timetable)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.person->{
                    val intent = Intent(this,Menu_Student::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.noticeboard->{
                    val intent = Intent(this,Noticeboard_Student::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.home->{
                    val intent = Intent(this,Display_Home::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.chat->{
                    val intent = Intent(this,ChatActivity_Student::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }
    }
}