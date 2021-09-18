package com.example.dialoges

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.Window
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {

    var myText = ""

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(R.string.simple_dialog)
            builder.setMessage(R.string.simpleMessage)
            builder.create()
                .show()
        }

        btnDialogButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(R.string.dialog_with_button)
            builder.setMessage(R.string.dialog_buttonMessage)
            builder.setPositiveButton(R.string.confirm)
            { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(applicationContext, R.string.confirmed, Toast.LENGTH_LONG).show()
            }
            val alert = builder.create()
            alert.show()
        }

        btnSingle.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
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
            val builder = AlertDialog.Builder(this@MainActivity)
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

            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

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

            val progressDialog = ProgressDialog(this)
            progressDialog.show()
        }


        btnBottom.setOnClickListener {

            val views: View = layoutInflater.inflate(R.layout.bottom_dialog, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(views)
            dialog.show()
        }

        btnCustom.setOnClickListener {
            val mDialogView = layoutInflater.inflate(R.layout.custom_layout, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            mBuilder.show()

        }


    }
}



