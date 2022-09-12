package com.example.hostelmanageapplication

import android.app.Activity
import android.app.ProgressDialog
import android.app.TaskInfo
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activiity_student_registration.*

class activiity_student_registration : AppCompatActivity() {
    var path : String?=null
    lateinit var filepath: Uri
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var root: DatabaseReference = FirebaseDatabase.getInstance().getReference("Image")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiity_student_registration)

        login.setOnClickListener({
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

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

        student_signup.setOnClickListener{
            if(student_reg_name.text.toString().isEmpty())
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
                    Toast.makeText(this,' '+{downloadurl}.toString(),Toast.LENGTH_SHORT).show()
                    var photo_data = root.child(root.push().key.toString()).setValue(filepath.toString())
                    //Toast.makeText(applicationContext,"Image Uploaded", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
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