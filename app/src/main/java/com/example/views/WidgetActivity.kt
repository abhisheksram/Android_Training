package com.example.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class WidgetActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        val iMA = intent
        val name = iMA.getStringExtra("Title")
        val actionBar = supportActionBar!!
        actionBar.title = name

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val imgBtn = findViewById<LinearLayout>(R.id.imgBtn)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)

        val chkBox1 = findViewById<CheckBox>(R.id.chkBox1)
        val chkBox2 = findViewById<CheckBox>(R.id.chkBox2)
        val chkBox3 = findViewById<CheckBox>(R.id.chkBox3)
        val radioButton1 = findViewById<RadioButton>(R.id.rb1)
        val radioButton2 = findViewById<RadioButton>(R.id.rb2)
        val radioButton3 = findViewById<RadioButton>(R.id.rb3)


        btn1.setOnClickListener { // Handler code here.
            val parentLayout = findViewById<View>(android.R.id.content)

            Snackbar.make(parentLayout, "Material Button 1 is Clicked", Snackbar.LENGTH_LONG)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(R.color.Red))
                .show()

        }
        btn2.setOnClickListener { // Handler code here.
            val parentLayout = findViewById<View>(android.R.id.content)

            Snackbar.make(parentLayout, "Material Button 2 is Clicked", Snackbar.LENGTH_LONG)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(R.color.Red))
                .show()

        }

        imgBtn.setOnClickListener { // Handler code here.
            val parentLayout = findViewById<View>(android.R.id.content)

            Snackbar.make(parentLayout, "Image Button is Clicked", Snackbar.LENGTH_LONG)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(R.color.Red))
                .show()

        }
        toggleButton.isChecked = false
        toggleButton.toggle()



        val parentLayout = findViewById<View>(android.R.id.content)

        chkBox1?.setOnCheckedChangeListener { _, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Checkbox 1."

            Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(R.color.Red))
                .show()

        }


        chkBox2?.setOnCheckedChangeListener { _, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Checkbox 2."

            Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(R.color.Red))
                .show()

        }

        chkBox3?.setOnCheckedChangeListener { _, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Checkbox 3."

            Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(R.color.Red))
                .show()

        }

        radioButton1.setOnClickListener { // Handler code here.
            Toast.makeText(applicationContext, "Radio Button 1 Clicked", Toast.LENGTH_SHORT).show()
        }

        radioButton2.setOnClickListener { // Handler code here.
            Toast.makeText(applicationContext, "Radio Button 2 Clicked", Toast.LENGTH_SHORT).show()
        }

        radioButton3.setOnClickListener { // Handler code here.
            Toast.makeText(applicationContext, "Radio Button 3 Clicked", Toast.LENGTH_SHORT).show()
        }

        val items = arrayOf("Option One", "Option Two", "Option Three")
        val spinner = findViewById<View>(R.id.spinner) as Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, items
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter



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
                val iMA = Intent(this, NextActivity::class.java)
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