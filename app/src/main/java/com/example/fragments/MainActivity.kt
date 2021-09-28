package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doLoadGraphToNav()
    }

    private fun doLoadGraphToNav() {
        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHost.findNavController().setGraph(R.navigation.nav_activity)
        navController = navHost.navController
    }
}

