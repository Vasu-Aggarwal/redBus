package com.example.redbus.ui.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.redbus.R
import com.example.redbus.ui.payment.paymentMethodActivity
import com.example.redbus.utilities.Constants
import com.example.redbus.utilities.SharedPref
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterUser : AppCompatActivity() {

    private lateinit var otpid: String
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        supportActionBar?.hide()

        val phoneNumber = intent.getStringExtra("number")

        val etOTP = findViewById<EditText>(R.id.etOTP)
        val btnOTP = findViewById<Button>(R.id.btnOTP)

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@RegisterUser, "Verification Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                otpid = verificationId
            }
        }


        btnOTP.setOnClickListener {
            if(etOTP.text.toString().isEmpty())
                Toast.makeText(this@RegisterUser, "Please enter the OTP", Toast.LENGTH_SHORT).show()
            else if(etOTP.text.toString().length != 6)
                Toast.makeText(this@RegisterUser, "Please enter correct OTP", Toast.LENGTH_SHORT).show()
            else{
                val cred = PhoneAuthProvider.getCredential(otpid, etOTP.text.toString())
                signInWithPhoneAuthCredential(cred)
            }
        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber!!)       // Phone number to verify
            .setTimeout(0L, java.util.concurrent.TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(this){ task->
            if(task.isSuccessful){
                user = task.getResult().user!!
                Toast.makeText(this@RegisterUser, "Verified", Toast.LENGTH_SHORT).show()
                SharedPref(this@RegisterUser).setString(Constants.USER_ID, user.uid)
                finish()
            }
            else{
                Toast.makeText(this@RegisterUser, "Failed to verify", Toast.LENGTH_SHORT).show()
            }
        }
    }
}