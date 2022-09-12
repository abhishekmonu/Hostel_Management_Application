package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_menu_student.*

class Menu_Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_student)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_menu)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.home->{
                    val intent = Intent(this,Display_Home::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.noticeboard->{
                    val intent = Intent(this,Noticeboard_Student::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.timetable->{
                    val intent = Intent(this,Timetable_Student::class.java)
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

        stu_menu_changepassword.setOnClickListener({
            val intent = Intent(this,Change_Password::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })
        stu_menu_logout.setOnClickListener({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

        attandance_student.setOnClickListener({
            val intent = Intent(this,QRScanner::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

        stu_menu_leavinghostel.setOnClickListener({
            val intent = Intent(this,Leaving_Student::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

    }
}