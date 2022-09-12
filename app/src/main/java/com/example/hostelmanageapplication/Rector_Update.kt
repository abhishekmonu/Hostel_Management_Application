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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_rector_update.*
import java.io.File

class Rector_Update : AppCompatActivity() {

    var path : String?=null
    var filepath: Uri ? = null
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var root: DatabaseReference = FirebaseDatabase.getInstance().getReference("Image")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_update)

        var bundle: Bundle? =intent.extras
        var enrollment1: String = bundle?.get("enrollno").toString()

        val bot = findViewById<BottomNavigationView>(R.id.bottomnavigation_admin_rectordata_update)

        bot.setOnItemReselectedListener {
            when(it.itemId)
            {
                R.id.Rector_Read->{
                    val intent = Intent(this,Rector_List::class.java)
                    intent.putExtra("enrollno",enrollment1)
                    startActivity(intent)
                }
                R.id.Rector_Insert->{
                    val intent = Intent(this,Rector_Insert::class.java)
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

        var searchenrollno: EditText = findViewById(R.id.enroll_rector_data)

        var rtname: EditText = findViewById(R.id.etrtname)
        var rtemail: EditText = findViewById(R.id.etrtemail)
        var rtmobno: EditText = findViewById(R.id.etrtmobno)
        var rtaddress: EditText = findViewById(R.id.etrtaddress)
        var rtenrollno: EditText = findViewById(R.id.etrtenrollno)
        var rtphoto: ImageView = findViewById(R.id.etrtphoto)
        var rtmale: RadioButton = findViewById(R.id.etrtmale)
        var rtfemale: RadioButton = findViewById(R.id.etrtfemale)
        var rtother: RadioButton = findViewById(R.id.etrtother)
        var rtfname: EditText = findViewById(R.id.etrtfname)
        var rtfmobno: EditText = findViewById(R.id.etrtfmobno)
        var rtbloodgroup: EditText = findViewById(R.id.etrtbloodgroup)
        var rtdob: EditText = findViewById(R.id.etrtdob)
        var rtoccupation: EditText = findViewById(R.id.etrtoccupation)
        var rthostelname: EditText = findViewById(R.id.etsthostelname)
        var rtdoj: EditText = findViewById(R.id.etrtdoj)
        var rtdol: EditText = findViewById(R.id.etrtdol)
        var rtpass: EditText = findViewById(R.id.etrtpass)
        var rtcpass: EditText = findViewById(R.id.etrtcpass)

        search_rector.setOnClickListener({

            if(searchenrollno.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Enrollment No", Toast.LENGTH_SHORT).show()
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

                        //val photo = it.child("photo").value.toString()
                        val photo = it.child("photo").value

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
                            rtphoto.setImageBitmap(bitmap)

                        }.addOnFailureListener{
                            Toast.makeText(this,"Not Found", Toast.LENGTH_SHORT).show()
                        }

                        rtname.setText(name.toString())
                        rtemail.setText(email.toString())
                        rtmobno.setText(mobileno.toString())
                        rtaddress.setText(address.toString())
                        rtenrollno.setText(enrollment.toString())
                        rtfname.setText(fathername.toString())
                        rtfmobno.setText(fathermobileno.toString())
                        rtbloodgroup.setText(bloodgroup.toString())
                        rtdob.setText(dob.toString())
                        rtoccupation.setText(occupation.toString())
                        rthostelname.setText(hostelname.toString())
                        rtpass.setText(password.toString())
                        rtcpass.setText(confirmpassword.toString())
                        rtdoj.setText(dateofjoining.toString())
                        rtdol.setText(dateofleaving.toString())

                        if(gender.toString().equals("Male"))
                        {
                            rtmale.setChecked(true)
                        }
                        else if(gender.toString().equals("Female"))
                        {
                            rtfemale.setChecked(true)
                        }
                        else
                        {
                            rtother.setChecked(true)
                        }

                    }
                    else
                    {
                        Toast.makeText(this,"Account Does Not Exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        rtphoto.setOnClickListener({
            selectImage()
        })

        submit_rector_update.setOnClickListener({
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
                        if(rtenrollno.text.toString().isEmpty())
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
                            var name = rtname.text.toString()
                            var email = rtemail.text.toString()
                            var mobileno = rtmobno.text.toString()
                            var address = rtaddress.text.toString()

                            if (rtmale.isChecked)
                            {
                                gender = rtmale.text.toString()
                            }
                            else if(rtfemale.isChecked)
                            {
                                gender = rtfemale.text.toString()
                            }
                            else
                            {
                                gender = rtother.text.toString()
                            }

                            var enrollment1 = rtenrollno.text.toString()
                            var fathername = rtfname.text.toString()
                            var fathermobno = rtfmobno.text.toString()
                            var bloodgroup = rtbloodgroup.text.toString()
                            var dob = rtdob.text.toString()
                            var password = rtpass.text.toString()
                            var confirmpassword = rtcpass.text.toString()
                            var occupation = rtoccupation.text.toString()
                            var hostelname = rthostelname.text.toString()
                            var dateofjoining = rtdoj.text.toString()
                            var dateofleaving=  rtdol.text.toString()
                            var role="Rector"

                            uploadFile(searchenrollno.text.toString())

                            var updaterector1 =  mapOf<String,String>(
                                "name" to name,
                                "email" to email,
                                "mobileno" to mobileno,
                                "address" to address,
                                "enrollment" to enrollment1,
                                "fathername" to fathername,
                                "fathermobileno" to fathermobno,
                                "bloodgroup" to bloodgroup,
                                "dob" to dob,
                                "gender" to gender,
                                "occupation" to occupation,
                                "hostelname" to hostelname,
                                "password" to password,
                                "confirmpassword" to confirmpassword,
                                "dateofjoining" to dateofjoining,
                                "dateofleaving" to dateofleaving,
                                "role" to role
                            )
                            database3.child(searchenrollno.text.toString()).updateChildren(updaterector1).addOnSuccessListener {
                                rtname.setText("")
                                rtemail.setText("")
                                rtmobno.setText("")
                                rtaddress.setText("")
                                rtenrollno.setText("")
                                rtfname.setText("")
                                rtfmobno.setText("")
                                rtbloodgroup.setText("")
                                rtdob.setText("")
                                rtoccupation.setText("")
                                rthostelname.setText("")
                                rtpass.setText("")
                                rtcpass.setText("")
                                rtdoj.setText("")
                                rtdol.setText("")
                                rtmale.setChecked(false)
                                rtfemale.setChecked(false)
                                rtother.setChecked(false)
                                rtphoto.setImageResource(R.drawable.gallery)
                                searchenrollno.setText("")
                                Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()

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
            etrtphoto.setImageBitmap(bitmap)
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
            imageRef?.putFile(filepath!!)
                ?.addOnSuccessListener { p0 ->
                    pd.dismiss()
                    val downloadurl: Task<Uri> = imageRef.downloadUrl
                    path = downloadurl.toString()
                    var photo_data = root.child(root.push().key.toString()).setValue(filepath.toString())
                    //Toast.makeText(applicationContext,"Image Uploaded", Toast.LENGTH_SHORT).show()
                    var photo = root.push().key.toString()
                    var updaterector3 =  mapOf<String,String>(
                        "photo" to photo
                    )
                    var db = FirebaseDatabase.getInstance().reference
                    db.child(searchenrollno).updateChildren(updaterector3)


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
            var sample = FirebaseDatabase.getInstance().getReference(searchenrollno)
            sample.get().addOnSuccessListener {
                if(it.exists())
                {
                    var photo = it.child("photo").value.toString()
                    var updaterector3 =  mapOf<String,String>(
                        "photo" to photo
                    )
                    var db = FirebaseDatabase.getInstance().reference
                    db.child(searchenrollno).updateChildren(updaterector3)
                }
            }
        }
    }
}