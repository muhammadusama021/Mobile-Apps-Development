package com.usamacreation.lab_assignment_1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.luminance

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gridView:CardView = findViewById(R.id.sampleProductCard);

        gridView.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,gridView)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buy ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.addTocart ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    }
                true
            })
            popupMenu.show()
        }
        val gridView1:CardView = findViewById(R.id.sampleProductCard2);

        gridView1.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,gridView1)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buy ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.addTocart ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
        val gridView2:CardView = findViewById(R.id.sampleProductCard3);

        gridView2.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,gridView2)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buy ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.addTocart ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
        val gridView3:CardView = findViewById(R.id.sampleProductCard4);

        gridView3.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,gridView3)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buy ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.addTocart ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
        val gridView4:CardView = findViewById(R.id.sampleProductCard5);

        gridView4.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,gridView4)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buy ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.addTocart ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
        val gridView5:CardView = findViewById(R.id.sampleProductCard6);

        gridView5.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,gridView5)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buy ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.addTocart ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)
        {

            R.id.search -> {
                val sharingIntent = Intent(Intent.ACTION_SEARCH)
                startActivity(Intent.createChooser(sharingIntent, "Search"))
                return true
            }
            R.id.notification -> {
                Toast.makeText(this, "Notification Clicked", Toast.LENGTH_LONG).show()
                /*startActivity(Intent(this, SettingActivity::class.java))*/
                return true


            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = "Here is the share content body"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
                return true
            }
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                return true
            }

            else->super.onOptionsItemSelected(item)
        }
    }
}