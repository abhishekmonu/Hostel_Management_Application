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
import kotlinx.android.synthetic.main.activity_rector_insert.*

class Rector_Insert : AppCompatActivity() {

    var path : String?=null
    lateinit var filepath: Uri
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var root: DatabaseReference = FirebaseDatabase.getInstance().getReference("Image")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_insert)

        var bundle: Bundle? =intent.extras
        var enrollment1: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_rectordata_insert)

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
                R.id.Rector_Delete->{
                    val intent = Intent(this,Rector_Delete::class.java)
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
        var imageView = findViewById<ImageView>(R.id.rector_reg_img)

        imageView.setOnClickListener{
            selectImage()
        }

        admin_rectordata_insert.setOnClickListener({
            if(rector_reg_enrollmentno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill All Details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var gender = ""
                var name = rector_reg_name.text.toString()
                var email = rector_reg_email.text.toString()
                var mobileno = rector_reg_mobno.text.toString()
                var address = rector_reg_address.text.toString()
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

                var enrollment = rector_reg_enrollmentno.text.toString()
                var fathername = rector_reg_fathername.text.toString()
                var fathermobno = rector_reg_fathermobno.text.toString()
                var bloodgroup = rector_reg_bloodgroup.text.toString()
                var dob = rector_reg_dob.text.toString()
                var password = rector_reg_pass.text.toString()
                var confirmpassword = rector_reg_confirmpass.text.toString()
                var occupation = rector_reg_occupation.text.toString()
                var hostelname = rector_reg_hostelname.text.toString()
                var dateofjoining = rector_reg_doj.text.toString()
                var dateofleaving= rector_reg_dol.text.toString()
                var role="Rector"

                //uploadFile()
                if(filepath != null)
                {
                    var pd = ProgressDialog(this)
                    pd.setTitle("Uploading...")
                    pd.show()
                    var imageRef: StorageReference? = storageReference?.child("images/"+enrollment)
                    imageRef?.putFile(filepath)
                        ?.addOnSuccessListener { p0 ->
                            pd.dismiss()
                            val downloadurl: Task<Uri> = imageRef.downloadUrl
                            path = downloadurl.toString()
                            var photo_data = root.child(enrollment).setValue(filepath.toString())
                            var photo = root.push().key.toString()
                            database.child(enrollment).setValue(Rector_Model(name,email,mobileno,address,gender,photo ,enrollment,fathername, fathermobno, bloodgroup,dob,hostelname,password,confirmpassword,occupation,dateofjoining,dateofleaving,role))
                            var database3 = FirebaseDatabase.getInstance().getReference(enrollment)
                            database3.child("photo").child(root.push().key.toString()).setValue(filepath.toString())

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
                else
                {
                    Toast.makeText(this,"Registered Failed",Toast.LENGTH_SHORT).show()
                }

                rector_reg_name.setText("")
                rector_reg_email.setText("")
                rector_reg_mobno.setText("")
                rector_reg_img.setImageResource(R.drawable.gallery)
                male.setChecked(false)
                female.setChecked(false)
                other.setChecked(false)
                rector_reg_doj.setText("")
                rector_reg_dol.setText("")
                rector_reg_address.setText("")
                rector_reg_enrollmentno.setText("")
                rector_reg_fathername.setText("")
                rector_reg_fathermobno.setText("")
                rector_reg_bloodgroup.setText("")
                rector_reg_dob.setText("")
                rector_reg_pass.setText("")
                rector_reg_occupation.setText("")
                rector_reg_confirmpass.setText("")
                rector_reg_hostelname.setText("")
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
            rector_reg_img.setImageBitmap(bitmap)
        }

    }
}