package com.example.androidtraining.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtraining.*
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class DialogsActivity: AppCompatActivity() {

    @SuppressLint("InflateParams", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)

        val actionBar = supportActionBar!!
        actionBar.title = "Dialogs"

        val btnSimple = findViewById<Button>(R.id.btnSimpleDialog)
        val btnDialogButton = findViewById<Button>(R.id.btnDialogButton)
        val btnSingle = findViewById<Button>(R.id.btnSingleChoice)
        val btnMulti = findViewById<Button>(R.id.btnMultiChoice)
        val btnCircular = findViewById<Button>(R.id.btnCircular)
        val btnDate = findViewById<Button>(R.id.btnDatePicker)
        val btnTime = findViewById<Button>(R.id.btnTimePicker)
        val btnBottom = findViewById<Button>(R.id.btnBottomDialog)
        val btnCustom = findViewById<Button>(R.id.btnCustom)

        val items = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")


        btnSimple.setOnClickListener {
            val builder = AlertDialog.Builder(this@DialogsActivity)
            builder.setTitle(R.string.simple_dialog)
            builder.setMessage(R.string.simpleMessage)
            builder.create()
                .show()
        }

        btnDialogButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@DialogsActivity)
            builder.setTitle(R.string.dialog_with_button)
            builder.setMessage(R.string.dialog_buttonMessage)
            builder.setCancelable(false)
            builder.setPositiveButton(R.string.confirm)
            { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(applicationContext, R.string.confirmed, Toast.LENGTH_LONG).show()
            }
            val alert = builder.create()
            alert.show()
        }

        btnSingle.setOnClickListener {
            val builder = AlertDialog.Builder(this@DialogsActivity)
            var selVal = 0
            builder.setTitle(R.string.singleMessage)
                .setSingleChoiceItems(items, -1) { _, which ->
                    selVal = which
                }
            builder.setPositiveButton("OK") { dialog, _ ->
                Toast.makeText(
                    applicationContext,
                    "You have selected: " + items[selVal],
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()

            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create()
                .show()

        }

        btnMulti.setOnClickListener {
            val builder = AlertDialog.Builder(this@DialogsActivity)
            val checkedItem = booleanArrayOf(false, false, false, false)
            val itemList = listOf(*items)
            var selItems = ""

            builder.setTitle(R.string.multi_choice)
                .setMultiChoiceItems(items, checkedItem) { _, which, isChecked ->
                    checkedItem[which] = isChecked

                }
            builder.setPositiveButton("OK") { dialog, _ ->
                for (i in checkedItem.indices) {
                    val checked = checkedItem[i]
                    if (checked) {
                        selItems += " " + itemList[i]

                    }

                }
                Toast.makeText(
                    applicationContext,
                    "You have selected: $selItems",
                    Toast.LENGTH_SHORT
                ).show()

                dialog.dismiss()

            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create()
                .show()

        }

        btnTime.setOnClickListener {

            val cal = Calendar.getInstance()
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)

            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minutes ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minutes)

                val timeShow = "Time selected is: " + SimpleDateFormat("HH:mm").format(cal.time)

                Toast.makeText(applicationContext, timeShow, Toast.LENGTH_SHORT).show()
            }
            TimePickerDialog(this, timeSetListener, hour, minute, true).show()

        }

        btnDate.setOnClickListener {

            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)

            val dateListener = DatePickerDialog(this, { _, mYear, mMonth, mDay ->

                val fMonth = mMonth + 1
                val dateShow = ("Date Selected: $mDay / $fMonth / $mYear")

                Toast.makeText(applicationContext, dateShow, Toast.LENGTH_SHORT).show()
            }, year, month, day)
            dateListener.show()
        }

        btnCircular.setOnClickListener {

                val builder = AlertDialog.Builder(this)
                //View view = getLayoutInflater().inflate(R.layout.progress);
                builder.setView(R.layout.custom_progress)
                val dialog = builder.create()
                dialog.show()

        }

        btnBottom.setOnClickListener {

            val views: View = layoutInflater.inflate(R.layout.bottom_dialog, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(views)
            dialog.show()

            val imgCamera = views.findViewById<ImageView>(R.id.imgCamera)
            val imgGallery = views.findViewById<ImageView>(R.id.imgGallery)

            imgCamera.setOnClickListener {
                dialog.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Camera",
                    Toast.LENGTH_SHORT
                ).show()
            }

            imgGallery.setOnClickListener {
                dialog.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Gallery",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        btnCustom.setOnClickListener {
            val views: View = layoutInflater.inflate(R.layout.custom_dialog, null)
            val btnSubmit = views.findViewById<Button>(R.id.btnSubmit)
            val btnX = views.findViewById<Button>(R.id.btnX)
            val myText = views.findViewById<EditText>(R.id.typeText)

            val builder = AlertDialog.Builder(this)
                .setView(views)
            val builderCreate = builder.create()

            btnSubmit.setOnClickListener {

                val userText: String = myText.text.toString()

                Toast.makeText(
                    applicationContext,
                    "You Have Entered: $userText",
                    Toast.LENGTH_SHORT
                ).show()
                builderCreate.dismiss()
            }

            btnX.setOnClickListener {
                Toast.makeText(applicationContext, "Cancelled", Toast.LENGTH_SHORT).show()
                builderCreate.dismiss()
            }
            builderCreate.show()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_widget -> {
                val iMA = Intent(this, WidgetActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_RecyclerView -> {
                val iMA = Intent(this, RecyclerActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Dialogs -> {
                val iMA = Intent(this, DialogsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_CustomListView -> {
                val iMA = Intent(this, MediasActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Maps -> {
                val iMA = Intent(this, MapsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Image -> {
                val iMA = Intent(this, GridActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Posts -> {
                val iMA = Intent(this, PostsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Tab -> {
                val iMA = Intent(this, TabLayoutViewPager::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_BottomNavigation -> {
                val iMA = Intent(this, BottomNavigationActivity::class.java)
                startActivity(iMA)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}