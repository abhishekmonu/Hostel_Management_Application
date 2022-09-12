package com.example.hostelmanageapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_otp_login.*
import java.util.concurrent.TimeUnit

class OTP_Login : AppCompatActivity() {

    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null

    private var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null

    private var mVerificationId:String? = null
    private lateinit var firebaseAuth: FirebaseAuth

    private val TAG = "MAIN_TAG"

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_login)

        var bundle: Bundle? =intent.extras
        var mobilenumber = bundle?.get("mobileno").toString()


        phoneEt.setText("+91"+mobilenumber)

        //init firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //init , setup progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        //verification state change callbacks, verification completed, verfication failed, code sent etc
        mCallBacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Log.d(TAG, "OnVerificationCompleted: ")
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                //This callback invoked in an invalid request for verification is made,
                // for instance if the phone number format is not valid.
                progressDialog.dismiss()
                Log.d(TAG, "OnVerificationFailed: ${e.message}")
                Toast.makeText(this@OTP_Login,"${e.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId:String,token: PhoneAuthProvider.ForceResendingToken)
            {

                Log.d(TAG,"onCodeSent: $verificationId")
                mVerificationId = verificationId
                forceResendingToken = token
                progressDialog.dismiss()

                Log.d(TAG, "OnCodesent: $verificationId")
                //hide Phone layout show code layout
                Toast.makeText(this@OTP_Login,"Verification Code sent...",Toast.LENGTH_SHORT).show()
            }

        }
        //PhoneContinueBtn click: input phone number , validate , start phone authentication/login
        resendCodeTv.setOnClickListener{
            //input phone number
            val phone = phoneEt.text.toString().trim()
            //validate phone number
            if(TextUtils.isEmpty(phone))
            {
                Toast.makeText(this,"Please Enter Phone Number",Toast.LENGTH_SHORT).show()
            }
            else
            {
                startPhoneNumberVerification(phone)
            }
        }
        //resendCodeTv click: if code didn't receive resend verification code/ OTP
        phoneContinuebtn.setOnClickListener{
            //input phone number
            val phone = phoneEt.text.toString().trim()
            //validate phone number
            if(TextUtils.isEmpty(phone))
            {
                Toast.makeText(this,"Please Enter Phone Number",Toast.LENGTH_SHORT).show()
            }
            else
            {
                resendVerficationCode(phone, forceResendingToken)
            }
        }
        //codeSubmitBtn click: input verification code, validate, verify phone number with verfication code
        CodeSubmitbtn.setOnClickListener{
            //input Verification code
            val code = otp_input.text.toString().trim()
            //input phone number
            if(TextUtils.isEmpty(code))
            {
                Toast.makeText(this,"Please Enter Verification Code",Toast.LENGTH_SHORT).show()
            }
            else
            {
                verifyPhoneNumberWithCode(mVerificationId,code)
            }
        }

    }
    private fun startPhoneNumberVerification(phone: String){
        Log.d(TAG, "startPhoneNumberVerification: $phone")
        progressDialog.setMessage("Verifying Phone Number...It take upto 10 to 15 seconds...")
        progressDialog.show()

        val options = mCallBacks?.let {
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .build()
        }
        options?.let { PhoneAuthProvider.verifyPhoneNumber(it) }
    }
    private fun resendVerficationCode(phone:String,token:PhoneAuthProvider.ForceResendingToken?){
        progressDialog.setMessage("Resend Code...")
        progressDialog.show()

        Log.d(TAG, "resendVerificationCode: $phone")
        val options = mCallBacks?.let {
            token?.let { it1 ->
                PhoneAuthOptions.newBuilder(firebaseAuth)
                    .setPhoneNumber(phone)
                    .setTimeout(60L,TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(it)
                    .setForceResendingToken(it1)
                    .build()
            }
        }
        options?.let { PhoneAuthProvider.verifyPhoneNumber(it) }
    }
    private fun verifyPhoneNumberWithCode(verificationId: String?, code:String){
        Log.d(TAG, "verifyPhoneNumberWithCode: $verificationId $code")
        progressDialog.setMessage("Verifying Code...")
        progressDialog.show()

        val credential = verificationId?.let { PhoneAuthProvider.getCredential(it,code) }
        credential?.let { signInWithPhoneAuthCredential(it) }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Log.d(TAG, "signInWithPhoneAuthCredential:")
        progressDialog.setMessage("Logging In")
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                val phone = firebaseAuth.currentUser?.phoneNumber
                Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
                //start Profile activity
                var bundle: Bundle? =intent.extras
                var enrollment: String = bundle?.get("enrollno").toString()
                var role: String = bundle?.get("role").toString()

                if(role.equals("Student"))
                {
                    var intent = Intent(this,Display_Home::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                else if(role.equals("Admin"))
                {
                    Toast.makeText(this,"Admin Login",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this,Admin_Profile::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
                else if(role.equals("Rector"))
                {
                    Toast.makeText(this,"Rector Login",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this,Rector_Profile::class.java)
                    intent.putExtra("enrollno",enrollment)
                    startActivity(intent)
                }
            }
            .addOnFailureListener{e->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

}