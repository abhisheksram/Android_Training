package com.example.androidtraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidtraining.activity.*
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.common.Constants
import com.example.androidtraining.util.showToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging


class MyActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        val tv = findViewById<TextView>(R.id.textViewMyACt)
        val data = intent.getStringExtra("navigate").toString()
        // tv.text = data.toString()

            tv.text = data
            goToActivity(data)

/*
        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
            }


        Firebase.messaging.token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            Constants.Notification.token = task.result
            //tv.text = Constants.Notification.token
        })
 */
    }

    private fun goToActivity(activity: String) {

        lateinit var intent: Intent

        when (activity) {
            "TabActivity" -> {
                intent = Intent(this, TabLayoutViewPager::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            "RecyclerActivity" -> {
                intent = Intent(this, RecyclerActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            "DialogsActivity" -> {
                intent = Intent(this, DialogsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            "WidgetActivity" -> {
                intent = Intent(this, WidgetActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            "PostsActivity" -> {
                intent = Intent(this, PostsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            "MediasActivity" -> {
                intent = Intent(this, MediasActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            "BottomNavigationActivity" -> {
                intent = Intent(this, BottomNavigationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            else -> {
                showToast("$activity doesn't exist")
            }
        }
    }
}