package com.usamacreation.lab_terminal_fa18_bse_021

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("TAG", "MyReceiver")
        val serviceIntent = Intent(context,service::class.java)
        context.startService(serviceIntent)

    }


}