package com.example.homework4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

class YozhikReceiver : BroadcastReceiver() {
    var callsCounter = 0

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "Loshadka!") {
            callsCounter++

            // Update the UI in the main thread
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                // Ensure you cast context to MainActivity before updating
                if (context is MainActivity) {
                    context.updateCounters(callsCounter)
                }
            }

            // Simulate thinking time
            Thread.sleep(3000)

            // Call back to send another broadcast back
            context?.sendBroadcast(Intent("Yozhik!"))
        }
    }
}