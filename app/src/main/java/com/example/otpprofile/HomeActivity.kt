package com.example.otpprofile

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Here Drawer code
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val drawerIcon : Toolbar = findViewById(R.id.drawerIcon)
        val drawerNavigation : NavigationView = findViewById(R.id.navigationDrawer)

        drawerNavigation.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.home -> Toast.makeText(this, "Home Fragment", Toast.LENGTH_SHORT).show()
                R.id.setting -> Toast.makeText(this, "setting Fragment", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(this, "profile Fragment", Toast.LENGTH_SHORT).show()

            }
            drawerLayout.close()
            true
        }
        drawerIcon.setNavigationOnClickListener { drawerLayout.open() }
        //Drawer code end

        // Navigation bar code

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottomNavigation)

        loadFragment(HomeFragment())
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){

                R.id.home -> loadFragment(HomeFragment())
                R.id.setting -> loadFragment(SettingFragment())
                R.id.profile -> loadFragment(profileFragment())
            }
            true
        }

    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}