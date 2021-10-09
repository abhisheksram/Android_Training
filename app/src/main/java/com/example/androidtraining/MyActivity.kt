package com.example.androidtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.messaging.FirebaseMessaging
import java.lang.Exception


class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        val tv = findViewById<TextView>(R.id.textViewMyACt)

        val fcmToken = FirebaseMessaging.getInstance().token

        tv.text = fcmToken.toString()



    }
}