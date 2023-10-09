package com.example.otpprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SettingFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    val data = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_setting, container, false)

     val  userId=Firebase.auth.currentUser?.uid.toString()
        val db=Firebase.firestore

        db.collection("rahul").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    Toast.makeText(requireContext(), "No data found for this user", Toast.LENGTH_SHORT).show()
                } else {

                    for (document in querySnapshot.documents) {
                        val userData = document.data
                        val userName = userData?.get("name") as String
                        val useremail=userData?.get("email")as String
                        val userage=userData?.get("age") as Long

                        val textView = view.findViewById<TextView>(R.id.getname)
                        val textemail=view.findViewById<TextView>(R.id.getemail)
                        val textage=view.findViewById<TextView>(R.id.getage)
                        textView.text = "$userName!"
                        textemail.text="$useremail"
                        textage.text="$userage"
                    }
                    // Display a success message
                    Toast.makeText(requireContext(), "Data retrieved successfully", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // This block is executed when there's an error
                Toast.makeText(requireContext(), "Failed to retrieve data: $exception", Toast.LENGTH_SHORT).show()
                // Handle the error, e.g., log it or display an error message
            }
        val delete=view.findViewById<AppCompatButton>(R.id.datadelete)
        delete.setOnClickListener {
            data.collection("rahul").document(userId).delete()
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "delete sucessfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "not delete", Toast.LENGTH_SHORT).show()
                }
        }
        return view



    }
}



























