package com.example.otpprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class HomeFragment: Fragment() {
    val auth = FirebaseAuth.getInstance()
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val logout = view.findViewById<Button>(R.id.logoutBtn)

        logout.setOnClickListener {
            auth.signOut()

            val intent =Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(requireActivity(),"LOGOUT", Toast.LENGTH_SHORT).show()
        }


        // Inflate the layout for this fragment
        return view
    }

}