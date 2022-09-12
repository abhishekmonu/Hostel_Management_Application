package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_student_list.*

class Student_list : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var studentRecycleView: RecyclerView
    private lateinit var studentArrayList: ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_studentdata)

        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Student_Insert->{
                    val intent = Intent(this,Student_Insert::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Student_Update->{
                    val intent = Intent(this,Student_Update::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Student_Delete->{
                    val intent = Intent(this,Student_Delete::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }

        studentdata_backarrow.setOnClickListener({
            val intent = Intent(this,admin_menu::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })


        studentRecycleView = findViewById(R.id.studentlist)
        studentRecycleView.layoutManager = LinearLayoutManager(this)
        studentRecycleView.setHasFixedSize(true)

        studentArrayList = arrayListOf<Student>()
        getStudentData()

    }

    private fun getStudentData() {
        database = FirebaseDatabase.getInstance().reference

        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(studentSnapshot in snapshot.children)
                    {
                        val user = studentSnapshot.getValue(Student::class.java)
                        studentArrayList.add(user!!)
                    }
                    studentRecycleView.adapter = Student_Adapter(studentArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}