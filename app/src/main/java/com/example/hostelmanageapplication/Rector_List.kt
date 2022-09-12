package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_rector_list.*

class Rector_List : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var RectorRecycleView: RecyclerView
    private lateinit var RectorArrayList: ArrayList<Rector>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_list)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_rectordata)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Rector_Insert->{
                    val intent = Intent(this,Rector_Insert::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Rector_Update->{
                    val intent = Intent(this,Rector_Update::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Rector_Delete->{
                    val intent = Intent(this,Rector_Delete::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }

        rectordata_backarrow.setOnClickListener({
            val intent = Intent(this,admin_menu::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

        RectorRecycleView = findViewById(R.id.rectorlist)
        RectorRecycleView.layoutManager = LinearLayoutManager(this)
        RectorRecycleView.setHasFixedSize(true)

        RectorArrayList = arrayListOf<Rector>()
        getRectorData()
    }

    private fun getRectorData() {
        database = FirebaseDatabase.getInstance().reference

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(RectorSnapshot in snapshot.children)
                    {
                        val user = RectorSnapshot.getValue(Rector::class.java)
                        RectorArrayList.add(user!!)
                    }
                    RectorRecycleView.adapter = Rector_Adapter(RectorArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}