package com.app.testapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", "From: ${remoteMessage.from}")

        remoteMessage.data.isNotEmpty().let {
            Log.d("FCM", "Message data payload: " + remoteMessage.data)
            handleNow()
        }

        remoteMessage.notification?.let {
            Log.d("FCM", "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
        Log.d("FCM", "Refreshed token: $token")
    }

    private fun handleNow() {
        Log.d("FCM", "Short lived task is done.")
    }
}
