package com.usamacreation.lab_terminal_fa18_bse_021

import android.Manifest
import android.app.PendingIntent
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.usamacreation.lab_terminal_fa18_bse_021.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var receiver:MyReceiver


    private lateinit var binding: ActivityMainBinding

    val SENT_ACTION="org.example.smsapp.SENT_ACTION"
    val DELIVERY_ACTION="org.example.DELIVERY_ACTION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* receiver= MyReceiver()*/

       /* IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {

            registerReceiver(receiver, it)
        }

        startService(Intent(this,service::class.java))
        stopService(Intent(this,service::class.java))*/


        val queue= Volley.newRequestQueue(this)

        val url = "https://run.mocky.io/v3/0085e3ac-c7d6-4a93-ba92-925f65be6fb3"
        var titleArray=ArrayList<String>()
        var quantityArray= ArrayList<Int>()
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...PLease wait")
        pDialog.show()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener{res->
                titleArray = ArrayList()
                quantityArray = ArrayList()
                Log.d("Response",res.toString())

                for(i in 0 until res.length()) {
                    var jsonObject = res.getJSONObject(i)
                    val title = jsonObject.optString("t")
                    val quantity = jsonObject.optString("q").toInt()
                    titleArray.add(title)
                    quantityArray.add(quantity)

                }
                pDialog.hide()
                Log.d("Title",titleArray.toString())
                Log.d("Quantity",quantityArray.toString())

                var adapter1 = ProductsAdaptor(titleArray,quantityArray)
                binding.recyclerView.adapter= adapter1
                adapter1.setOnItemClickListener(object:ProductsAdaptor.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        send_SMS(position)
                    }


                })
                binding.recyclerView.layoutManager = GridLayoutManager(this,1)

            },



            Response.ErrorListener { error ->
                pDialog.hide()
                Log.d("Cant Manage request", error.toString())

            })
        // Add JsonRequest to the RequestQueue
        queue.add(jsonObjectRequest)
        Log.d("Title",titleArray.toString())
        Log.d("Quantity",quantityArray.toString())




    }
    private fun send_SMS(position: Int) {
        val SENT_ACTION="org.example.kotlin_terminal_app.SENT_ACTION"
        val DELIVERY_ACTION="org.example.DELIVERY_ACTION"

        val sentIntent = PendingIntent.getBroadcast(this,
            100,
            Intent(SENT_ACTION), 0)

        val deliveryIntent = PendingIntent.getBroadcast(this, 200, Intent(DELIVERY_ACTION), 0)
        val sentRecvr=object: BroadcastReceiver()
        {
            override fun onReceive(p0: Context?, p1: Intent?) {
                Log.d("SMS ", "sent")
            }

        }
        val it= IntentFilter(SENT_ACTION)
        registerReceiver(sentRecvr, it)

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("SMS ", "BUY")
            }
        }, IntentFilter(DELIVERY_ACTION))
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)== PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)

        {
            var sms= SmsManager.getDefault()
            var tNumb = "(650) 555-1212"

            sms.sendTextMessage(tNumb,
                "1234",
                "BUY",
                sentIntent,deliveryIntent)
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS),111)
        }
    }





}