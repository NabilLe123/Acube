package com.app.acube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnvHome = findViewById<BottomNavigationView>(R.id.bnv_home)
        bnvHome.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_insert -> {
                    loadFragment(InsertFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_display -> {
                    loadFragment(DisplayFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        //default fragment
        bnvHome.selectedItemId = R.id.navigation_insert
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_home, fragment)
            .commitAllowingStateLoss()
    }
}