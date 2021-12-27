package com.usamacreation.lab_terminal_fa18_bse_021

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class service : Service() {

        /** Called when the activity is first created.  */
        override fun onCreate() {
            super.onCreate()
            Log.d("TAG", "Service created.")
        }

        override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
            return START_STICKY
        }

        override fun onStart(intent: Intent?, startId: Int) {
            super.onStart(intent, startId)
            Log.i(TAG, "I am Started ")
        }
        override fun onDestroy() {
            Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show()
            Log.d(TAG, "I am onDestroy")
        }


        override fun onBind(arg0: Intent): IBinder? {
            return null
        }

}