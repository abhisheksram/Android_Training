package com.example.androidtraining

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationCompat
import com.example.androidtraining.activity.*
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.common.Constants
import com.example.androidtraining.util.showToast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFireBaseService : FirebaseMessagingService() {

    private lateinit var notificationIntent: Intent
    override fun onNewToken(token: String) {
        showToast(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null) {

            val navigate = remoteMessage.data["navigate"]!!
            //val clickAction = remoteMessage.notification!!.clickAction

            Handler(Looper.getMainLooper()).post {
                showNotification(
                    remoteMessage.notification!!.title!!,
                    remoteMessage.notification!!.body!!,
                    navigate
                )
            }

        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun showNotification(title: String, message: String, action: String) {

        val builder: Notification

        when (action) {
            "TabActivity" -> {
                notificationIntent = Intent(this, TabLayoutViewPager::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            "RecyclerActivity" -> {
                notificationIntent = Intent(this, RecyclerActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            "DialogsActivity" -> {
                notificationIntent = Intent(this, DialogsActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            "WidgetActivity" -> {
                notificationIntent = Intent(this, WidgetActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            "PostsActivity" -> {
                notificationIntent = Intent(this, PostsActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            "MediasActivity" -> {
                notificationIntent = Intent(this, MediasActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            "BottomNavigationActivity" -> {
                notificationIntent = Intent(this, BottomNavigationActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

            else -> {
                notificationIntent = Intent(this, WidgetActivity::class.java)
                notificationIntent.putExtra("navigate", action)
                Handler(Looper.getMainLooper()).post {
                    showToast("$action doesn't exist")
                }
            }
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder = NotificationCompat.Builder(applicationContext, Constants.Notification.channelID)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.icons_notification)
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    Constants.Notification.channelID,
                    Constants.Notification.channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(0, builder)
        } else {
            notificationManager.notify(0, builder)
        }
    }

}