package com.example.advancedlab.lab1.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
//广播接收器
class AirplaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.AIRPLANE_MODE") {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)
            val msg = if (isAirplaneModeOn) "飞行模式已开启" else "飞行模式已关闭"
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}