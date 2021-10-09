package com.example.androidtraining


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtraining.fragments.LoginFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.beginTransaction().replace(R.id.containerView, LoginFragment()).commit()

    }
}