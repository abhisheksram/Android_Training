package com.example.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        val iMA = intent
        val name = iMA.getStringExtra("Title")
        val actionBar = supportActionBar!!
        actionBar.title = name
        val text = findViewById<View>(R.id.lblWidget) as TextView
        text.text = name
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_widget -> {
                val iMA = Intent(this, WidgetActivity::class.java)
                iMA.putExtra("Title", "Widget")
                startActivity(iMA)
                finish()
            }
            R.id.action_RecyclerView -> {
                val iMA = Intent(this, MainActivity::class.java)
                iMA.putExtra("Title", "Recycler View")
                startActivity(iMA)
                finish()
            }
            R.id.action_ListView -> {
                val iMA = Intent(this, ListActivity::class.java)
                iMA.putExtra("Title", "List View")
                startActivity(iMA)
                finish()
            }
            R.id.action_CustomListView -> {
                val iMA = Intent(this, NextActivity::class.java)
                iMA.putExtra("Title", "Custom List View")
                startActivity(iMA)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}