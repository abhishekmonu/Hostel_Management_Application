package com.example.hostelmanageapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_qrcode_generator.*

class QRCodeGenerator : AppCompatActivity() {
    private lateinit var qrcode: ImageView
    private lateinit var etdata: EditText
    private lateinit var btngenerateqrcode: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_generator)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        qrcode_backarrow.setOnClickListener({
            val intent = Intent(this,admin_menu::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })

        qrcode = findViewById(R.id.qrcode)
        etdata = findViewById(R.id.etdata)
        btngenerateqrcode = findViewById(R.id.btngenerateqrcode)

        btngenerateqrcode.setOnClickListener({
            val data = etdata.text.toString().trim()

            if(data.isEmpty())
            {
                Toast.makeText(this, "Enter Some Data", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val writer = QRCodeWriter()
                try
                {
                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE,1024,1024)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565)

                    for (x in 0 until width){
                        for (y in 0 until height){
                            bmp.setPixel(x,y,if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }   
                    qrcode.setImageBitmap(bmp)
                }
                catch (e : WriterException){
                    e.printStackTrace()
                }
            }
        })
    }
}