package com.example.android_training_task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext, "onCreate Called", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart Called", Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart Called", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause Called", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume Called", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop Called", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy Called", Toast.LENGTH_LONG).show()
    }
}
