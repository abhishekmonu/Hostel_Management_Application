package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_student_delete.*

class Student_Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_delete)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_studentdata_delete)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Student_Read->{
                    val intent = Intent(this,Student_list::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Student_Update->{
                    val intent = Intent(this,Student_Update::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Student_Insert->{
                    val intent = Intent(this,Student_Insert::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }

        var stu_enrollno = findViewById<TextInputEditText>(R.id.student_delete_enrollno)
        student_delete_delete.setOnClickListener{
            if(stu_enrollno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Enrollment Number",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var database = FirebaseDatabase.getInstance().reference
                database.child(stu_enrollno.text.toString()).removeValue().addOnSuccessListener {
                    stu_enrollno.setText("")
                    Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this,"Failed to Delete",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}