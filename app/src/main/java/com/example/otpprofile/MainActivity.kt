package com.example.otpprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(){

    var verificationId = " "
    private var auth= FirebaseAuth.getInstance()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mobile = findViewById<EditText>(R.id.mobile)
        val verify = findViewById<EditText>(R.id.verifyOtp)
        val sendotp = findViewById<Button>(R.id.sendOtp)
        val verifyotp = findViewById<Button>(R.id.verify)
//        val registration = findViewById<Button>(R.id.registration)


        sendotp.setOnClickListener {
            sendOtp(mobile.text.toString())
        }
        verifyotp.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(verificationId,verify.text.toString())
            verifyOtp(credential)
        }



//        registration.setOnClickListener {
//            val intent = Intent(this, Registration::class.java)
//            startActivity(intent)
//
//        }

        // Gradle -> signingReport

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                verifyOtp(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId= p0
            }

        }

    }
    fun sendOtp(phone : String){
        val phoneAuthOtpions = PhoneAuthOptions.newBuilder()
            .setPhoneNumber("+91 $phone")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(callbacks)

            .setActivity(this)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOtpions)
    }

    fun verifyOtp(crossinline : PhoneAuthCredential){
        auth.signInWithCredential(crossinline)
            .addOnSuccessListener {
//                val uid= it.user?.uid
                 val intent = Intent(this, Registration::class.java)
                startActivity(intent)

                Toast.makeText(this,"Verify OTP ", Toast.LENGTH_SHORT).show()


            }
            .addOnFailureListener {
                Toast.makeText(this@MainActivity,"InCorrect OTP " , Toast.LENGTH_SHORT).show()

            }
    }

}