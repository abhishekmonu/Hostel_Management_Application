package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_rector_menu.*

class Rector_Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_menu)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_rector_menu)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.home->{
                    val intent = Intent(this,Rector_Profile::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.chat->{
                    val intent = Intent(this,Rector_chat::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.timetable->{
                    val intent = Intent(this,Rector_timetable::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.noticeboard->{
                    val intent = Intent(this,Rector_noticeboard::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }

        rector_menu_leaving_check.setOnClickListener({
            val intent = Intent(this,Rector_Leave_Check::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })
        rector_menu_payment_check.setOnClickListener({
            val intent = Intent(this,Rector_Payment::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })
    }
}