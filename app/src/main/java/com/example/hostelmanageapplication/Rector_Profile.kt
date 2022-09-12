package com.example.hostelmanageapplication

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class Rector_Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_profile)

        var bundle: Bundle? =intent.extras
        var enrollment = bundle?.get("enrollno").toString()

        var r_name:TextView = findViewById(R.id.rector_home_name)
        var r_email:TextView = findViewById(R.id.rector_home_email)
        var r_mobno:TextView = findViewById(R.id.rector_home_mobno)
        var r_address:TextView = findViewById(R.id.rector_home_address)
        var r_enroll:TextView = findViewById(R.id.rector_home_enrollno)
        var r_photo:ImageView = findViewById(R.id.rector_home_photo)
        var r_hostelname:TextView = findViewById(R.id.rector_home_hostelname)
        var r_occupation:TextView = findViewById(R.id.rector_home_occupation)
        var r_gender:TextView = findViewById(R.id.rector_home_gender)
        var r_bloodgroup:TextView = findViewById(R.id.rector_home_bloodgroup)
        var r_dob:TextView = findViewById(R.id.rector_home_dob)
        var r_fname:TextView = findViewById(R.id.rector_home_fname)
        var r_fmobno:TextView = findViewById(R.id.rector_home_fmobno)
        var r_doj:TextView = findViewById(R.id.rector_home_doj)
        var r_dol:TextView = findViewById(R.id.rector_home_dol)

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_rector_profile)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.noticeboard->{
                    val intent = Intent(this,Rector_noticeboard::class.java)
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
                R.id.person->{
                    val intent = Intent(this,Rector_Menu::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
        }

        var database = FirebaseDatabase.getInstance().getReference(enrollment)
        database.get().addOnSuccessListener {
            if(it.exists())
            {
                val name = it.child("name").value
                val email = it.child("email").value
                val mobileno = it.child("mobileno").value
                val address = it.child("address").value
                val enrollment = it.child("enrollment").value
                val fathername = it.child("fathername").value
                val fathermobileno = it.child("fathermobno").value
                val bloodgroup = it.child("bloodgroup").value
                val dob = it.child("dob").value
                val gender = it.child("gender").value
                val occupation = it.child("occupation").value
                val hostelname = it.child("hostelname").value
                val password= it.child("password").value
                val confirmpassword= it.child("confirmpassword").value
                val dateofjoining = it.child("dateofjoining").value
                val dateofleaving= it.child("dateofleaving").value
                val photo = it.child("photo").value

                val storageref = FirebaseStorage.getInstance().reference.child("images/$photo")

                val localfile = File.createTempFile("tempImage","jpeg")
                storageref.getFile(localfile).addOnSuccessListener {

                    val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                    r_photo.setImageBitmap(bitmap)

                }.addOnFailureListener{
                    Toast.makeText(this,"Not Found", Toast.LENGTH_SHORT).show()
                }

                r_name.text = name.toString()
                r_email.text = email.toString()
                r_mobno.text = mobileno.toString()
                r_address.text = address.toString()
                r_enroll.text = enrollment.toString()
                r_hostelname.text = hostelname.toString()
                r_occupation.text= occupation.toString()
                r_gender.text = gender.toString()
                r_bloodgroup.text = bloodgroup.toString()
                r_dob.text = dob.toString()
                r_fname.text = fathername.toString()
                r_fmobno.text =fathermobileno.toString()
                r_doj.text = dateofjoining.toString()
                r_dol.text = dateofleaving.toString()

            }
        }

    }
}