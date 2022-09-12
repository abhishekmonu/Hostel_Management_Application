package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_rector_delete.*

class Rector_Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_delete)

        var bundle: Bundle? =intent.extras
        var enrollment1: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_rectordata_delete)

        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Rector_Read->{
                    val intent = Intent(this,Rector_List::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
                R.id.Rector_Update->{
                    val intent = Intent(this,Rector_Update::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
                R.id.Rector_Insert->{
                    val intent = Intent(this,Rector_Insert::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
            }
        }

        var rector_enrollno = findViewById<TextInputEditText>(R.id.rector_delete_enrollno)
        rector_delete_delete.setOnClickListener{
            if(rector_enrollno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Enrollment Number", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var database = FirebaseDatabase.getInstance().reference
                database.child(rector_enrollno.text.toString()).removeValue().addOnSuccessListener {
                    rector_enrollno.setText("")
                    Toast.makeText(this,"Successfully Deleted", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this,"Failed to Delete", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}