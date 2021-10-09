package com.example.androidtraining

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.androidtraining.activity.DialogsActivity
import com.example.androidtraining.activity.PostsActivity
import com.example.androidtraining.activity.TabLayoutViewPager
import com.example.androidtraining.activity.WidgetActivity
import com.example.androidtraining.common.Constants
import com.example.androidtraining.util.showToast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFireBaseService : FirebaseMessagingService() {
    private lateinit var notificationIntent : Intent

    override fun onNewToken(token: String) {
        Log.d("FCMToken", "Refreshed token: $token")

        showToast(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {


        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FCMData", "Message data payload: ${remoteMessage.data}")

        }

        if (remoteMessage.notification != null) {

            val navigate = remoteMessage.data["navigate"]!!
            //val clickAction = remoteMessage.notification!!.clickAction
            showNotification(
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!,
                navigate
            )
        }

    }

    private fun getRemote(title: String, message: String): RemoteViews {

        val remoteViews = RemoteViews(packageName, R.layout.custom_notfication)
        remoteViews.setTextViewText(R.id.tv_Notification_Title, title)
        remoteViews.setTextViewText(R.id.tv_Notification_Body, message)

        return remoteViews
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun showNotification(title: String, message: String, action: String) {

        if (action == "TabActivity"){
        notificationIntent = Intent(this, TabLayoutViewPager::class.java)
        notificationIntent.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP}

        if (action == "RecyclerActivity"){
            notificationIntent = Intent(this, RecyclerActivity::class.java)
            notificationIntent.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP}

        if (action == "DialogsActivity"){
            notificationIntent = Intent(this, DialogsActivity::class.java)
            notificationIntent.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP}

        if (action == "WidgetActivity"){
            notificationIntent = Intent(this, WidgetActivity::class.java)
            notificationIntent.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP}

        if (action == "PostsActivity"){
            notificationIntent = Intent(this, PostsActivity::class.java)
            notificationIntent.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP}

        if (action == "NextActivity"){
            notificationIntent = Intent(this, PostsActivity::class.java)
            notificationIntent.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP}


        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, Constants.Notification.channelID)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)


        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setContent(getRemote(title, message))
        } else {
            builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.icons_notification)
        }

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
        } else {
            notificationManager.notify(0, builder.build())
        }
    }
}