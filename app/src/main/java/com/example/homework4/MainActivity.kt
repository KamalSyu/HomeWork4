package com.example.homework4

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var receiver: YozhikReceiver

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.MyTextView)

        val filter = IntentFilter("Loshadka!")
        receiver = YozhikReceiver()  // Initialize the receiver
        registerReceiver(receiver, filter, RECEIVER_EXPORTED)

        // Call Loshadka
        sendBroadcast(Intent("Loshadka!"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)  // Unregister the receiver to prevent memory leaks
    }

    override fun onResume() {
        super.onResume()
        updateCounters(receiver.callsCounter) // Pass the callsCounter
    }

    fun updateCounters(callsCounter: Int) {
        textView.text = "Ёжик услышал Лошадку $callsCounter раз(а)"
    }
}