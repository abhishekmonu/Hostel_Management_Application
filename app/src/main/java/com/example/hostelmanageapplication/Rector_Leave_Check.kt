package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_rector_leave_check.*

class Rector_Leave_Check : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var LeavingRecycleView: RecyclerView
    private lateinit var LeavingArrayList: ArrayList<Leaving>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_leave_check)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        leavingdata_backarrow.setOnClickListener({
            val intent = Intent(this,Rector_Menu::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

        LeavingRecycleView = findViewById(R.id.leavinglist)
        LeavingRecycleView.layoutManager = LinearLayoutManager(this)
        LeavingRecycleView.setHasFixedSize(true)

        LeavingArrayList = arrayListOf<Leaving>()
        getRectorData()
    }

    private fun getRectorData() {
        database = FirebaseDatabase.getInstance().getReference("Leaving_Hostel")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(LeavingSnapshot in snapshot.children)
                    {
                        val user = LeavingSnapshot.getValue(Leaving::class.java)
                        LeavingArrayList.add(user!!)
                    }
                    LeavingRecycleView.adapter = Leaving_Adapter(LeavingArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}