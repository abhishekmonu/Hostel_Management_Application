package com.example.hostelmanageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_rector_payment.*

class Rector_Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rector_payment)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        rector_payment_backarrow.setOnClickListener({
            val intent = Intent(this,Rector_Menu::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

        var account = findViewById<TextInputEditText>(R.id.rector_payment_accountno)
        var ifsc = findViewById<TextInputEditText>(R.id.rector_payment_ifsc)
        var branch = findViewById<TextInputEditText>(R.id.rector_payment_branch)
        var bankname = findViewById<TextInputEditText>(R.id.rector_payment_bankname)


        rector_payment_Submit.setOnClickListener({
            if(account.text.toString().isEmpty() || ifsc.text.toString().isEmpty() || branch.text.toString().isEmpty() || bankname.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Fill All Details", Toast.LENGTH_SHORT).show()
            }
            else {
                val database2 = FirebaseDatabase.getInstance().getReference("PaymentDetails")
                database2.setValue(PaymentDetail_Model(account.text.toString(), ifsc.text.toString(), branch.text.toString(), bankname.text.toString(),))
                Toast.makeText(this,"PaymentDetails Set Successfully", Toast.LENGTH_SHORT).show()
            }
        })
    }
}