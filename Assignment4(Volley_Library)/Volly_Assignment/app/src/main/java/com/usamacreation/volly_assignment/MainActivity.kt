package com.usamacreation.volly_assignment

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1:Button=findViewById(R.id.btn2)
        val imagebtn:Button=findViewById(R.id.btn6)
        val stringbtn:Button=findViewById(R.id.btn3)
        val addParam:Button=findViewById(R.id.btn4)
        val addHeader:Button=findViewById(R.id.btn5)
        btn1.setOnClickListener({
            Request_Json()
        })
        imagebtn.setOnClickListener({
            Request_Image()
        })
        stringbtn.setOnClickListener({
            Request_String()
        })
        addParam.setOnClickListener({
            Add_Post_parameter()
        })
        addHeader.setOnClickListener({
            Add_Request_headers()
        })
    }
      /*####### (1) Request Json format ########*/
    private fun Request_Json() {
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...PLease wait")
        pDialog.show()
        val view:TextView=findViewById(R.id.ViewBox)

        val queue= Volley.newRequestQueue(this)

        val url = "https://run.mocky.io/v3/cc1eb953-b35f-49b0-b20e-e43232921464"

        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> { response ->
                    view.isVisible = true
                    view.text = response.toString()
                    pDialog.hide();
                },
                Response.ErrorListener { error ->
                    view.text = "Can't load request"
                    pDialog.hide();
                    Log.d("Cant manage request", error.toString())

                })
          // Add JsonRequest to the RequestQueue
        queue.add(jsonObjectRequest)
    }

    /*####### (2) Request String format ########*/
    private fun Request_String() {
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...PLease wait")
        pDialog.show()
        val view:TextView=findViewById(R.id.ViewBox)

        val queue= Volley.newRequestQueue(this)

        val url = "https://run.mocky.io/v3/0ad19eed-9ae0-4a7c-ad96-a1f1b9b1bd3c"
        val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener<String> { response ->
                    view.isVisible = true
                    view.text = response
                    pDialog.hide();
                },
                Response.ErrorListener { error ->
                    view.text = "Can't load request"
                    pDialog.hide();
                    Log.d("Cant manage request", error.toString())

                })
        // Add StringRequest to the RequestQueue
        queue.add(stringRequest)
    }

    /*####### (3) Request Image  ########*/
    private fun Request_Image()
    {
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...PLease wait")
        pDialog.show()

        val image:ImageView=findViewById(R.id.image2)
        val queue= Volley.newRequestQueue(this)
        val mImageURLString="https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832_960_720.jpg"
        val imageRequest = ImageRequest(
                mImageURLString, // Image URL
                Response.Listener<Bitmap> { response ->
                    image.setImageBitmap(response)
                    pDialog.hide();
                },
                250, // Image width
                250, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
                Response.ErrorListener() { // Error listener
                    Response.ErrorListener { error ->
                        pDialog.hide();
                        Toast.makeText(this,"Can't manage request",Toast.LENGTH_LONG).show()
                        Log.d("Cant manage request", error.toString())

                    }
                }
        );
        // Add ImageRequest to the RequestQueue
        queue.add(imageRequest);
    }

    /*####### (4) Adding Post Paramters ########*/
    private fun Add_Post_parameter()
    {
        Toast.makeText(this,"Adding Post Parameter",Toast.LENGTH_LONG).show()
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...PLease wait")
        pDialog.show()

        val view:TextView=findViewById(R.id.ViewBox)
        val queue= Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/cc1eb953-b35f-49b0-b20e-e43232921464"

        val params = HashMap<String,String>()
        params["name"] = "Muhammad Usama"
        params["Class"] = "BSE-7B"
        params["Reg_No"] = "FA18-BSE-021"
        val jsonObject = JSONObject(params as Map<*, *>)

        // Volley post request with parameters
        val PostParam = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                Response.Listener { response ->
                    view.text = "Response: $response"
                    pDialog.hide();
                },
                Response.ErrorListener{error->
                    // Error in request
                    view.text = "Volley error: $error"
                    pDialog.hide();
        })
        // Add the volley post request to the request queue
        queue.add(PostParam)

    }

    /*####### (5) Adding Request Headers ########*/
    private fun Add_Request_headers()
    {
        Toast.makeText(this,"Adding Request Headers",Toast.LENGTH_LONG).show()
    }
}