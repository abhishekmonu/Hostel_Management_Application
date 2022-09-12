package com.example.hostelmanageapplication

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_student_update.*
import java.io.File

class Student_Update : AppCompatActivity() {
    var path : String?=null
    lateinit var filepath: Uri
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var root: DatabaseReference = FirebaseDatabase.getInstance().getReference("Image")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_update)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_studentdata_update)
        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Student_Read->{
                    val intent = Intent(this,Student_list::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                R.id.Student_Insert->{
                    val intent = Intent(this,Student_Insert::class.java)
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

        var searchenrollno: EditText = findViewById(R.id.enroll_stu_data)

        var stname:EditText = findViewById(R.id.etstname)
        var stemail:EditText = findViewById(R.id.etstemail)
        var stmobno:EditText = findViewById(R.id.etstmobno)
        var staddress:EditText = findViewById(R.id.etstaddress)
        var stenrollno:EditText = findViewById(R.id.etstenrollno)
        var stphoto:ImageView = findViewById(R.id.etstphoto)
        var stsemester:EditText = findViewById(R.id.etstsemester)
        var stdepartment:EditText = findViewById(R.id.etstdepartment)
        var stmale:RadioButton = findViewById(R.id.etstmale)
        var stfemale:RadioButton = findViewById(R.id.etstfemale)
        var stother:RadioButton = findViewById(R.id.etstother)
        var stAC:RadioButton = findViewById(R.id.etstAC)
        var stNonAC:RadioButton = findViewById(R.id.etstNonAC)
        var stfname:EditText = findViewById(R.id.etstfname)
        var stfmobno:EditText = findViewById(R.id.etstfmobno)
        var stbloodgroup:EditText = findViewById(R.id.etstbloodgroup)
        var stcollege:EditText = findViewById(R.id.etstcollege)
        var styear:EditText = findViewById(R.id.etstyear)
        var stdob:EditText = findViewById(R.id.etstdob)
        var sthostelstatus:EditText = findViewById(R.id.etsthostelstatus)
        var sthostelname:EditText = findViewById(R.id.etsthostelname)
        var stroomno:EditText = findViewById(R.id.etstroomno)
        var sttotalfee:EditText = findViewById(R.id.etsttotalfee)
        var stfeepaid:EditText = findViewById(R.id.etstfeepaid)
        var stfeepending:EditText = findViewById(R.id.etstfeepending)
        var stdoj:EditText = findViewById(R.id.etstdoj)
        var stdol:EditText = findViewById(R.id.etstdol)
        var stpass:EditText = findViewById(R.id.etstpass)
        var stcpass:EditText = findViewById(R.id.etstcpass)

        search_stu.setOnClickListener({

            if(searchenrollno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Enrollment No",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var database = FirebaseDatabase.getInstance().getReference(searchenrollno.text.toString())
                database.get().addOnSuccessListener {
                    if(it.exists())
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
                        val password= it.child("password").value
                        val confirmpassword= it.child("confirmpassword").value
                        val dateofjoining = it.child("dateofjoining").value
                        val dateofleaving= it.child("dateofleaving").value

                        //val photo = it.child("photo").value.toString()

                        val photo = enrollment

                        /*var database3: DatabaseReference = FirebaseDatabase.getInstance().getReference(searchenrollno.text.toString()).child("photo")
                        database3.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var value = snapshot.getValue<String>()
                                Picasso.get().load(value).into(stphoto)
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }
                        })*/
                        val storageref = FirebaseStorage.getInstance().reference.child("images/$photo")

                        val localfile = File.createTempFile("tempImage","jpeg")
                        storageref.getFile(localfile).addOnSuccessListener {

                            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                            stphoto.setImageBitmap(bitmap)

                        }.addOnFailureListener{
                            Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show()
                        }




                        stname.setText(name.toString())
                        stemail.setText(email.toString())
                        stmobno.setText(mobileno.toString())
                        staddress.setText(address.toString())
                        stenrollno.setText(enrollment.toString())
                        stsemester.setText(semester.toString())
                        stdepartment.setText(department.toString())
                        stfname.setText(fathername.toString())
                        stfmobno.setText(fathermobileno.toString())
                        stbloodgroup.setText(bloodgroup.toString())
                        stdob.setText(dob.toString())
                        stcollege.setText(college.toString())
                        styear.setText(year.toString())
                        stroomno.setText(roomno.toString())
                        sthostelstatus.setText(hostelstatus.toString())
                        sthostelname.setText(hostelname.toString())
                        sttotalfee.setText(totalfee.toString())
                        stfeepaid.setText(feepaid.toString())
                        stfeepending.setText(feepending.toString())
                        stpass.setText(password.toString())
                        stcpass.setText(confirmpassword.toString())
                        stdoj.setText(dateofjoining.toString())
                        stdol.setText(dateofleaving.toString())
                        if(gender.toString().equals("Male"))
                        {
                            stmale.setChecked(true)
                        }
                        else if(gender.toString().equals("Female"))
                        {
                            stfemale.setChecked(true)
                        }
                        else
                        {
                            stother.setChecked(true)
                        }
                        if(hosteltype.toString().equals("AC"))
                        {
                            stAC.setChecked(true)
                        }
                        else
                        {
                            stNonAC.setChecked(true)
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Account Does Not Exists",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

        stphoto.setOnClickListener({
            selectImage()
        })

        submit_update.setOnClickListener({
            if(searchenrollno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Enrollment No",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var database1 = FirebaseDatabase.getInstance().getReference(searchenrollno.text.toString())
                database1.get().addOnSuccessListener{
                    if(it.exists())
                    {
                        if(stenrollno.text.toString().isEmpty())
                        {
                            Toast.makeText(this,"Please Fill the details",Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            storage = FirebaseStorage.getInstance()
                            storageReference = storage!!.getReference()
                            var database3 = FirebaseDatabase.getInstance().reference

                            var gender = ""
                            var hosteltype = ""
                            var name = stname.text.toString()
                            var email = stemail.text.toString()
                            var mobileno = stmobno.text.toString()
                            var address = staddress.text.toString()

                            if (stmale.isChecked)
                            {
                                gender = stmale.text.toString()
                            }
                            else if(stfemale.isChecked)
                            {
                                gender = stfemale.text.toString()
                            }
                            else
                            {
                                gender = stother.text.toString()
                            }
                            if (stAC.isChecked)
                            {
                                hosteltype = stAC.text.toString()
                            }
                            else
                            {
                                hosteltype = stNonAC.text.toString()
                            }

                            var enrollment1 = stenrollno.text.toString()
                            var semester = stsemester.text.toString().toInt()
                            var department = stdepartment.text.toString()
                            var fathername = stfname.text.toString()
                            var fathermobno = stfmobno.text.toString()
                            var bloodgroup = stbloodgroup.text.toString()
                            var college = stcollege.text.toString()
                            var year = styear.text.toString().toInt()
                            var dob = stdob.text.toString()
                            var password = stpass.text.toString()
                            var confirmpassword = stcpass.text.toString()

                            var hostelstatus = sthostelstatus.text.toString()
                            var hostelname = sthostelname.text.toString()
                            var totalfee = sttotalfee.text.toString()
                            var feepaid= stfeepaid.text.toString()
                            var feepending= stfeepending.text.toString()
                            var dateofjoining = stdoj.text.toString()
                            var dateofleaving=  stdol.text.toString()
                            var role="Student"
                            var roomno= stroomno.text.toString().toInt()

                            //uploadFile(searchenrollno.text.toString())
                            var sample = FirebaseDatabase.getInstance().getReference(searchenrollno.text.toString())
                            sample.get().addOnSuccessListener {
                                if(it.exists())
                                {
                                    var photo = it.child("photo").value.toString()
                                    var updatestudent3 =  mapOf<String,String>(
                                        "photo" to photo
                                    )
                                    var db = FirebaseDatabase.getInstance().reference
                                    db.child(searchenrollno.text.toString()).updateChildren(updatestudent3)
                                }
                            }

                            var updatestudent1 =  mapOf<String,String>(
                                        "name" to name,
                                        "email" to email,
                                        "mobileno" to mobileno,
                                        "address" to address,
                                        "enrollment" to enrollment1,
                                        "department" to department,
                                        "fathername" to fathername,
                                        "fathermobileno" to fathermobno,
                                        "bloodgroup" to bloodgroup,
                                        "dob" to dob,
                                        "college" to college,
                                        "hosteltype" to hosteltype,
                                        "gender" to gender,
                                        "hostelstatus" to hostelstatus,
                                        "hostelname" to hostelname,
                                        "totalfee" to totalfee,
                                        "feepaid" to feepaid,
                                        "feepending" to feepending,
                                        "password" to password,
                                        "confirmpassword" to confirmpassword,
                                        "dateofjoining" to dateofjoining,
                                        "dateofleaving" to dateofleaving,
                                        "role" to role
                            )

                            var updatestudent2 = mapOf<String,Int>(
                                "semester" to semester,
                                "year" to year,
                                "roomno" to roomno
                                )

                            database3.child(searchenrollno.text.toString()).updateChildren(updatestudent1).addOnSuccessListener {
                                stname.setText("")
                                stemail.setText("")
                                stmobno.setText("")
                                staddress.setText("")
                                stenrollno.setText("")
                                stdepartment.setText("")
                                stfname.setText("")
                                stfmobno.setText("")
                                stbloodgroup.setText("")
                                stdob.setText("")
                                stcollege.setText("")
                                sthostelstatus.setText("")
                                sthostelname.setText("")
                                sttotalfee.setText("")
                                stfeepaid.setText("")
                                stfeepending.setText("")
                                stpass.setText("")
                                stcpass.setText("")
                                stdoj.setText("")
                                stdol.setText("")
                                stmale.setChecked(false)
                                stfemale.setChecked(false)
                                stother.setChecked(false)
                                stAC.setChecked(false)
                                stNonAC.setChecked(false)
                                stphoto.setImageResource(R.drawable.gallery)
                                database3.child(searchenrollno.text.toString()).updateChildren(updatestudent2).addOnSuccessListener{
                                    stsemester.setText("")
                                    styear.setText("")
                                    stroomno.setText("")
                                    searchenrollno.setText("")
                                    Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
                                }
                            }.addOnFailureListener{

                                Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Account Does Not Exists",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    private fun selectImage() {
        var intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"Select Image From here.."),111)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 111 && resultCode == Activity.RESULT_OK && data != null )
        {
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            etstphoto.setImageBitmap(bitmap)
        }
    }
    private fun uploadFile(searchenrollno:String)
    {
        if(filepath != null)
        {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading...")
            pd.show()
            var imageRef: StorageReference? = storageReference?.child("images/" +root.push().key)
            imageRef?.putFile(filepath)
                ?.addOnSuccessListener { p0 ->
                    pd.dismiss()
                    val downloadurl: Task<Uri> = imageRef.downloadUrl
                    path = downloadurl.toString()
                    var photo_data = root.child(root.push().key.toString()).setValue(filepath.toString())
                    //Toast.makeText(applicationContext,"Image Uploaded", Toast.LENGTH_SHORT).show()
                }
                ?.addOnFailureListener{ p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()
                }
                ?.addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }
        }
        else
        {
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }
    }


}