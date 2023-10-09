package com.example.otpprofile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val userName = findViewById<EditText>(R.id.userName)
        val userAge = findViewById<EditText>(R.id.userAge)
        val userEmail = findViewById<EditText>(R.id.userEmail)
        val userPhone = findViewById<EditText>(R.id.userPhone)
        val button = findViewById<Button>(R.id.button)



        button.setOnClickListener {

            var userId = auth.currentUser?.uid.toString()
            if(userId == null){
                Toast.makeText(this@Registration, "please login first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var collection = Firebase.firestore.collection("rahul")

            var userData =UserModel(

                userName.text.toString(),
                userAge.text.toString().toLong(),
                userEmail.text.toString(),
                userPhone.text.toString().toLong(),
                userId

            )
            //set
            collection.document(userId).set(userData).addOnSuccessListener {

                Toast.makeText(this@Registration, "Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
                .addOnFailureListener {
                    Toast.makeText(this@Registration, "fail", Toast.LENGTH_SHORT).show()

                }

//            collection.document(userId).get()
//                .addOnSuccessListener {
//                    var data = it.toObject(UserModel::class.java)
//                }
//                .addOnFailureListener {
//
//                }
        }
    }
}