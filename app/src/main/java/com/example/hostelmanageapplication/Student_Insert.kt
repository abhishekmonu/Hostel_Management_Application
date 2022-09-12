package com.example.hostelmanageapplication

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_student_insert.*

class Student_Insert : AppCompatActivity() {

    var path : String?=null
    lateinit var filepath: Uri
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var root: DatabaseReference = FirebaseDatabase.getInstance().getReference("Image")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_insert)

        var bundle: Bundle? =intent.extras
        var enrollment1: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_studentdata_insert)

        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Student_Read->{
                    val intent = Intent(this,Student_list::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
                R.id.Student_Update->{
                    val intent = Intent(this,Student_Update::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
                R.id.Student_Delete->{
                    val intent = Intent(this,Student_Delete::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
            }
        }

        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.getReference()
        var male = findViewById<RadioButton>(R.id.male)
        var female = findViewById<RadioButton>(R.id.female)
        var other = findViewById<RadioButton>(R.id.other)
        var database = FirebaseDatabase.getInstance().reference
        var imageView = findViewById<ImageView>(R.id.student_reg_img)

        imageView.setOnClickListener{
            selectImage()
        }

        admin_studentdata_insert.setOnClickListener{
            if(student_reg_enrollmentno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill All Details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var gender = ""
                var hosteltype = ""
                var name = student_reg_name.text.toString()
                var email = student_reg_email.text.toString()
                var mobileno = student_reg_mobno.text.toString()
                var address = student_reg_address.text.toString()
                if (male.isChecked)
                {
                    gender = male.text.toString()
                }
                else if(female.isChecked)
                {
                    gender = female.text.toString()
                }
                else
                {
                    gender = other.text.toString()
                }
                if (AC.isChecked)
                {
                    hosteltype = AC.text.toString()
                }
                else
                {
                    hosteltype = NonAC.text.toString()
                }
                var enrollment = student_reg_enrollmentno.text.toString()
                var semester = student_reg_semester.text.toString().toInt()
                var department = student_reg_department.text.toString()
                var fathername = student_reg_fathername.text.toString()
                var fathermobno = student_reg_fathermobno.text.toString()
                var bloodgroup = student_reg_bloodgroup.text.toString()
                var college = student_reg_college.text.toString()
                var year = student_reg_year.text.toString().toInt()
                var dob = student_reg_dob.text.toString()
                var password = student_reg_pass.text.toString()
                var confirmpassword = student_reg_confirmpass.text.toString()

                var hostelstatus = "Pending"
                var hostelname = "Not Set"
                var totalfee = "31000"
                var feepaid="Not set"
                var feepending="Not set"
                var dateofjoining = "30-04-2022"
                var dateofleaving="Not Found"
                var role="Student"
                var roomno=0

                uploadFile()

                var photo = filepath.toString()
                database.child(enrollment).setValue(Student_Model(name,email,mobileno,address,gender,photo ,enrollment, semester, department, fathername, fathermobno, bloodgroup, college, year, dob, hosteltype ,password, confirmpassword,hostelstatus,hostelname,totalfee,feepaid,feepending,dateofjoining,dateofleaving,role,roomno))

                student_reg_name.setText("")
                student_reg_email.setText("")
                student_reg_mobno.setText("")
                student_reg_img.setImageResource(R.drawable.gallery)
                male.setChecked(false)
                female.setChecked(false)
                other.setChecked(false)
                AC.setChecked(false)
                NonAC.setChecked(false)
                student_reg_address.setText("")
                student_reg_enrollmentno.setText("")
                student_reg_semester.setText("")
                student_reg_department.setText("")
                student_reg_fathername.setText("")
                student_reg_fathermobno.setText("")
                student_reg_bloodgroup.setText("")
                student_reg_college.setText("")
                student_reg_year.setText("")
                student_reg_dob.setText("")
                student_reg_pass.setText("")
                student_reg_confirmpass.setText("")

            }
        }

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
            student_reg_img.setImageBitmap(bitmap)
        }

    }
    private fun uploadFile()
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
                    Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()

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
    }
}