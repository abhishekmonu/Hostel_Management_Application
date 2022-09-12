package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_admin_menu.*

class admin_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_menu)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.home->{
                    val intent = Intent(this,Admin_Profile::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.noticeboard->{
                    val intent = Intent(this,admin_noticeboard::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.timetable->{
                    val intent = Intent(this,admin_timetable::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.chat->{
                    val intent = Intent(this,admin_chat::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }

        admin_menu_changepassword.setOnClickListener({
            val intent = Intent(this,admin_changepassword::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

        admin_menu_logout.setOnClickListener({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })
        admin_menu_student_data.setOnClickListener({
            val intent = Intent(this,Student_list::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

       qrcodegenerator.setOnClickListener({
           val intent = Intent(this,QRCodeGenerator::class.java)
           intent.putExtra("enrollno",enrollment)
           startActivity(intent)
       })
        admin_menu_rectordata.setOnClickListener({
            val intent = Intent(this,Rector_List::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })




    }
}