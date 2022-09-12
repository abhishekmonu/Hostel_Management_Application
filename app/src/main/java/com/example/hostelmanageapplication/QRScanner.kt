package com.example.hostelmanageapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
/*import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode*/
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_qrscanner.*

class QRScanner : AppCompatActivity() {
    //private lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        var bundle: Bundle? =intent.extras
        var enrollment: String = bundle?.get("enrollno").toString()

        /*
        qrscanner_backarrow.setOnClickListener({
            val intent = Intent(this,Menu_Student::class.java)
            intent.putExtra("enrollno",enrollment)
            startActivity(intent)
        })


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        }
        else
        {
            startScanning()
        }
    }
    private fun startScanning() {
        val scannerView: CodeScannerView = findViewById(R.id.scanner)
        codeScanner = CodeScanner(this, scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread{

                var bundle: Bundle? =intent.extras
                var enrollment: String = bundle?.get("enrollno").toString()

                var attandance = it.text.toString()
                Toast.makeText(this,"${enrollment}: ${attandance}", Toast.LENGTH_SHORT).show()

                var location = ""
                var database = FirebaseDatabase.getInstance().getReference("Attandance")
                database.child(enrollment).setValue(Attandance_Model(enrollment,attandance,location))

            }
        }
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread{
                Toast.makeText(this,"Camere Initialization error", Toast.LENGTH_SHORT).show()
            }
        }
        scannerView.setOnClickListener({
            codeScanner.startPreview()
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 123)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Camere Permission Granted",Toast.LENGTH_SHORT).show()
                startScanning()
            }
            else
            {
                Toast.makeText(this,"Camere Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(::codeScanner.isInitialized){
            codeScanner?.startPreview()
        }
    }

    override fun onPause() {
        if(::codeScanner.isInitialized){
            codeScanner?.releaseResources()
        }
        super.onPause()
    */}
}