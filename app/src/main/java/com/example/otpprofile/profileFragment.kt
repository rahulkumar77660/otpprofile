package com.example.otpprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class profileFragment : Fragment() {
    val auth = FirebaseAuth.getInstance()
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val db = Firebase.firestore
        val userId= Firebase.auth.currentUser?.uid.toString()


        val editname=view.findViewById<EditText>(R.id.NameEdit)
        val btn=view.findViewById<AppCompatButton>(R.id.editbtn)
        db.collection("Users").document(userId).get().addOnSuccessListener {
            if (it != null) {
                val name1 = it.data?.get("name")?.toString()
                editname?.setText(name1)

            }
        }
        btn.setOnClickListener {

            db.collection("rahul").document(userId).update("name", editname.text.toString())
                .addOnSuccessListener {
//                    val intent =Intent(requireActivity(),SettingFragment::class.java)
//                    startActivity(intent)



                    Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT)
                        .show()
//                    edit_name.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                    }
            }
        return view
    }

}