package com.app.testapp.ui.listscreen

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.testapp.R
import com.app.testapp.base.BaseActivity
import com.app.testapp.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : BaseActivity(), MainNavigator {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigator = this
        initToolBarTitle("Test title")
        setupRecyclerView()
        fetchList()
        observeViewModel()
        askNotificationPermission()
        getFCMToken()

        binding.toolbar.rtlToolbarView.setOnClickListener {
            sendNotification()
        }

    }

    private fun setupRecyclerView() {
        adapter = MainAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { repositories ->
            repositories?.let { adapter.updateList(it) }
        }
    }

    private fun fetchList(){
        viewModel.fetchRepositories()
    }

    private fun askNotificationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS
                    ),
                    1001
                )
            }
        }
    }

    private fun getFCMToken() {

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    Log.d(
                        "FCM_TOKEN",
                        it.result
                    )
                }
            }
    }

    private fun sendNotification() {

        val channelId = "test_channel"

        val notificationManager =
            getSystemService(
                NotificationManager::class.java
            )

        val channel = NotificationChannel(
            channelId,
            "Test Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channel)

        // Build notification
        val notification =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Test Notification")
                .setContentText("Notification from button click 🚀")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

        // Show notification
        notificationManager.notify(
            1,
            notification
        )
    }

    override fun onSearchClicked() {

    }
}