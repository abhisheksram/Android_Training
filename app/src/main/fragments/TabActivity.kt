package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.fragments.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class TabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        val actionBar = supportActionBar!!
        actionBar.title = "Tab Layout"

        supportFragmentManager.beginTransaction().replace(R.id.tabFragmentContainer, TabFragment1()).commit()

        val tabLayout :TabLayout = findViewById(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.tabFragmentContainer, TabFragment1()).commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.tabFragmentContainer, TabFragment2()).commit()
                    }
                    else -> {
                        supportFragmentManager.beginTransaction().replace(R.id.tabFragmentContainer, TabFragment3()).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

}