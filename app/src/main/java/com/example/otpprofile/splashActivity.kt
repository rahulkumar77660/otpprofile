package com.example.otpprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class Splash_Activity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

      Handler().postDelayed({

        if (auth.currentUser?.uid!=null){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


           finish()
        }, 2000)
    }
}