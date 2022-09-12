package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_home.*
import org.w3c.dom.Text

class Display_Home : AppCompatActivity() {
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var database2: DatabaseReference = FirebaseDatabase.getInstance().getReference("19012011078")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_home)

        var bundle: Bundle? =intent.extras
        var enrollment = bundle?.get("enrollno").toString()


        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_home)
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


        var s_name: TextView = findViewById(R.id.home_name)
        var s_email: TextView = findViewById(R.id.home_email)
        var s_mobileno: TextView = findViewById(R.id.home_mob_no)
        var s_enrollment: TextView = findViewById(R.id.home_enroll_no)
        var s_semester: TextView = findViewById(R.id.home_semester)
        var s_department: TextView = findViewById(R.id.home_department)
        var s_fathername: TextView = findViewById(R.id.home_father_name)
        var s_fathermobno: TextView = findViewById(R.id.home_father_mobno)
        var s_bloodgroup: TextView = findViewById(R.id.home_bloodgroup)
        var s_college: TextView = findViewById(R.id.home_college)
        var s_year: TextView = findViewById(R.id.home_year)
        var s_hosteltype: TextView = findViewById(R.id.home_hosteltype)
        var s_photo:ImageView = findViewById(R.id.home_photo)
        var s_dob:TextView = findViewById(R.id.home_dob)
        var s_address:TextView = findViewById(R.id.home_address)
        var s_gender:TextView = findViewById(R.id.home_gender)

        var s_hostelstatus:TextView = findViewById(R.id.home_hostelstatus)
        var s_hostelname:TextView = findViewById(R.id.home_hostelname)
        var s_roomno:TextView = findViewById(R.id.home_roomno)
        var s_totalfee:TextView = findViewById(R.id.home_totalfee)
        var s_feepaid:TextView = findViewById(R.id.home_fee_paid)
        var s_feepending:TextView = findViewById(R.id.home_feepending)
        var s_dateofjoining:TextView = findViewById(R.id.home_date_of_joining)
        var s_dateofleaving:TextView = findViewById(R.id.home_date_of_leaving)


        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.getReference()
        var database = FirebaseDatabase.getInstance().getReference(enrollment)
        database.get().addOnSuccessListener({
            if (it.exists())
            {
                val name = it.child("name").value
                val email = it.child("email").value
                val mobileno = it.child("mobileno").value
                val address = it.child("address").value
                val enrollment = it.child("enrollment").value
                val semester = it.child("semester").value
                val department = it.child("department").value
                val fathername = it.child("fathername").value
                val fathermobileno = it.child("fathermobno").value
                val bloodgroup = it.child("bloodgroup").value
                val dob = it.child("dob").value
                val college = it.child("college").value
                val year = it.child("year").value
                val hosteltype = it.child("hosteltype").value

                val gender = it.child("gender").value
                val hostelstatus = it.child("hostelstatus").value
                val hostelname = it.child("hostelname").value
                val roomno = it.child("roomno").value
                val totalfee = it.child("totalfee").value
                val feepaid= it.child("feepaid").value
                val feepending= it.child("feepending").value
                val dateofjoining = it.child("dateofjoining").value
                val dateofleaving= it.child("dateofleaving").value

                val imag = it.child("photo").value

                var bundle: Bundle? =intent.extras
                var enrollmentphoto = bundle?.get("enrollno").toString()

                var database3: DatabaseReference = FirebaseDatabase.getInstance().getReference(enrollmentphoto).child("photo")
                database3.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var value = snapshot.getValue<String>()
                        Picasso.get().load(value).into(s_photo)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
                s_name.text = name.toString()
                s_email.text = email.toString()
                s_mobileno.text = mobileno.toString()
                s_enrollment.text = enrollment.toString()
                s_semester.text = semester.toString()
                s_department.text = department.toString()
                s_fathermobno.text = fathermobileno.toString()
                s_fathername.text = fathername.toString()
                s_bloodgroup.text = bloodgroup.toString()
                s_college.text = college.toString()
                s_year.text = year.toString()
                s_hosteltype.text = hosteltype.toString()
                s_dob.text = dob.toString()
                s_address.text = address.toString()
                s_gender.text = gender.toString()
                s_hostelstatus.text = hostelstatus.toString()
                s_hostelname.text = hostelname.toString()
                s_roomno.text = roomno.toString()
                s_totalfee.text = totalfee.toString()
                s_feepaid.text = feepaid.toString()
                s_feepending.text = feepending.toString()
                s_dateofjoining.text = dateofjoining.toString()
                s_dateofleaving.text = dateofleaving.toString()

            }
        })

    }
}
