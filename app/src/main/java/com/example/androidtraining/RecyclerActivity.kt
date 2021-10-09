package com.example.androidtraining

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.activity.*
import com.example.androidtraining.adapters.AndroidAdapter
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.data.models.MyData
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class RecyclerActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var androidList: ArrayList<MyData>
    private lateinit var ver: String
    private lateinit var dateShow : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar!!
        actionBar.title = "Recycler View"

        androidList = ArrayList()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        initView()
        loadData()
        initClicks()

    }

    private fun initClicks() {
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            addInfo()
        }
    }

    private fun initView() {
        recyclerView.apply {
            adapter = AndroidAdapter(::onItemClicked, ::showDeleteAlert)
        }
    }

    private fun loadData() {
        (recyclerView.adapter as AndroidAdapter).androidList =
            myData()
    }

    private fun myData(): ArrayList<MyData> {
        androidList.add(
            MyData(
                "A", "Alpha", "Version: 1", "23-09-2008", "SDK: 1",
                "Android 1.0, the first commercial version of the software"
            )
        )
        androidList.add(
            MyData(
                "B", "Beta", "Version: 1.1", "09-02-2009", "SDK: 2",
                "Introduced features like support for third-party keyboards, video recording, video playback, and copy-paste feature for the web browser."
            )
        )
        androidList.add(
            MyData(
                "C", "CupCake", "Version: 1.5", "27-04-2009", "SDK: 3",
                "It is the first Android version to officially get a dessert nickname. Android 1.5 was known as Android Cupcake and it was developed by Google with API 3."
            )
        )

        androidList.add(
            MyData(
                "D", "Donut", "Version: 1.6", "15-09-2009", "SDK: 4",
                "It introduced some distinctive features such as support for CDMA technology, support for different screen sizes, " +
                        "and a battery usage indicator that was also first introduced on this version."
            )
        )
        androidList.add(
            MyData(
                "E", "Eclair", "Version: 2", "02-10-2009", "SDK: 5",
                "Eclair offered features like an improved UI with a Google search bar on the top and it also supported HTML 5"
            )
        )
        androidList.add(
            MyData(
                "F", "Froyo", "Version: 2.2", "20-05-2010", "SDK: 8",
                "This version was unveiled during Google I/O 2010 conference and it offered several optimizations"
            )
        )
        androidList.add(
            MyData(
                "G", "Gingerbread", "Version: 2.2", "06-12-2010", "SDK: 9",
                "This version introduced features like NFC support and VoIP calls."
            )
        )

        androidList.add(
            MyData(
                "H", "Honeycomb", "Version: 3.0", "11-02-2011", "SDK: 11",
                "This version of Android was designed for large screen devices like tablets"
            )
        )

        androidList.add(
            MyData(
                "I", "Ice Cream Sandwich", "Version: 4.0", "18-10-2011", "SDK: 14",
                "This version was designed to offer a unified experience for both smartphones and tablets. " +
                        "It was also the first Android version to support Face Unlock on select devices."
            )
        )

        androidList.add(
            MyData(
                "J", "Jelly Bean", "Version: 4.1", "09-07-2012", "SDK: 16",
                "Android Jelly Bean is also officially the 10th iteration of Android and it was developed to offer performance " +
                        "improvements along with smooth user experience when compared to Android 4.0"
            )
        )

        androidList.add(
            MyData(
                "K", "KitKat", "Version: 4.4", "31-10-2013", "SDK: 19",
                "This version of Android was developed to offer an improved user experience on devices with limited hardware capabilities."
            )
        )
        androidList.add(
            MyData(
            "L", "Lollipop", "Version: 5.0", "04-11-2014", "SDK: 21",
            "This version of Android offered a redesigned UI and it also replaced Dalvik with ART or " +
                    "Android Runtime to improve application performance and battery optimization."
        )
        )

        androidList.add(
            MyData(
            "M", "Marshmallow", "Version: 6.0", "02-10-2015", "SDK: 23",
            "It offered a new permission architecture to improve user-privacy and " +
                    "it also natively supported USB Type-C port and a physical fingerprint sensor"
        )
        )

        androidList.add(
            MyData(
            "N", "Nougat", "Version: 7.0", "22-08-2016", "SDK: 24",
            "Nougat offered support for Vulkan API for better graphics rendering along with a new app notification format."
        )
        )

        androidList.add(
            MyData(
            "O", "Oreo", "Version: 8.0", "21-08-2017", "SDK: 26",
            "Oreo was the first Android version to support Bluetooth 5.0 and wide color gamut."
        )
        )

        androidList.add(
            MyData(
            "P", "Pie", "Version: 9", "06-08-2018", "SDK: 28",
            "It offered a refreshed material design with a new style of navigation buttons." +
                    " Till today, a lot of smartphones are still based on Android 9 Pie."
        )
        )

        androidList.add(
            MyData(
            "Q", "Quince Tart", "Version: 10", "03-09-2019", "SDK: 29",
            "This version was known as Android Q at the time of development and this is the first modern Android OS that doesn't have a dessert code name. " +
                    "It offered a complete full-screen user-interface with a redesigned navigation system, which is a bit similar to the modern iPhones."
        )
        )

        androidList.add(
            MyData(
            "R", "Red Velvet Cake", "Version: 11", "08-09-2020", "SDK: 30",
            "It comes with features like conversation notifications and this is also the first official Android version to offer a built-in screen recorder"
        )
        )

        androidList.add(
            MyData(
            "S", "Snow Cone", "Version: 12", "Not released", "SDK: 31",
            "Not Available"
        )
        )

        return androidList
    }

    private fun addInfo() {
        val views: View = layoutInflater.inflate(R.layout.custom_add, null)

        val verName = views.findViewById<EditText>(R.id.txtVersionName)
        val desc = views.findViewById<EditText>(R.id.txtDesc)
        val btnAdd = views.findViewById<Button>(R.id.btnAddData)
        val datePick = views.findViewById<Button>(R.id.datePick)

        val builder = AlertDialog.Builder(this)
        builder.setView(views)
        val builderCreate = builder.create()

        val itemsVersion = arrayOf("Select Version","Version: 1", "Version: 1.1","Version: 1.5","Version: 1.6","Version: 2","Version: 2.2","Version: 2.3",
            "Version: 3","Version: 4.0","Version: 4.1","Version: 4.4","Version: 5.0","Version: 6.0","Version: 7.0",
            "Version: 8.0","Version: 9.0","Version: 10","Version: 11","Version: 12"  )

        val versionSpinner = views.findViewById<Spinner>(R.id.spinnerVersion)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, itemsVersion
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        versionSpinner.adapter = adapter


        datePick.setOnClickListener {
            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)

            val dateListener = DatePickerDialog(this, { _, mYear, mMonth, dayOfMonth ->

                val month1 = mMonth + 1
                val myMonth = if (month1 < 10) "0$month1" else {
                    "$month1"
                }
                val myDay = if (dayOfMonth < 10) "0$dayOfMonth" else {
                    "$dayOfMonth"
                }
                dateShow = "$myDay-$myMonth-$mYear"
                if (dateShow.isEmpty()) Toast.makeText(applicationContext, "Provide Date", Toast.LENGTH_SHORT).show()
                else datePick.text = dateShow

            }, year, month, day)
            dateListener.show()

        }

        val itemsSDK = arrayOf("Select SDK","SDK: 1","SDK: 2","SDK: 3","SDK: 4","SDK: 5","SDK: 8","SDK: 9","SDK: 11",
            "SDK: 14","SDK: 16","SDK: 19","SDK: 21","SDK: 23","SDK: 24","SDK: 26","SDK: 28","SDK: 29","SDK: 30","SDK: 31" )
        val sdkSpinner = views.findViewById<Spinner>(R.id.spinnerSDK)
        val sdkAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, itemsSDK
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sdkSpinner.adapter = sdkAdapter


        btnAdd.setOnClickListener {

            val myNewVersion: String = versionSpinner.selectedItem.toString()
            val myNewVersionName: String =verName.text.toString()
            val myNewSDK: String = sdkSpinner.selectedItem.toString()
            val myNewDate: String = datePick.text.toString()
            val myNewDesc : String = desc.text.toString()
            when {
                myNewVersionName.isEmpty() -> {
                    Toast.makeText(applicationContext,
                        "Version Name is required",
                        Toast.LENGTH_SHORT).show()  // change message as per the field!
                }
                myNewVersion == "Select Version" -> {
                    Toast.makeText(applicationContext,
                        "Select Version",
                        Toast.LENGTH_SHORT).show()  // change message as per the field!
                }

                myNewDate == "Release Date" -> {
                    Toast.makeText(applicationContext,
                        "Add release date",
                        Toast.LENGTH_SHORT).show()  // change message as per the field!
                }
                myNewSDK == "Select SDK" -> {
                    Toast.makeText(applicationContext,
                        "Select SDK",
                        Toast.LENGTH_SHORT).show()  // change message as per the field!
                }
                myNewDesc.isEmpty() -> {
                    Toast.makeText(applicationContext,
                        "Description should not be empty",
                        Toast.LENGTH_SHORT).show()  // change message as per the field!
                }
                else -> {

                    ver = myNewVersionName[0].toString()
                    androidList.add(
                        MyData(
                            ver,
                            myNewVersionName,
                            myNewVersion,
                            myNewDate,
                            myNewSDK,
                            myNewDesc
                        )
                    )
                    (recyclerView.adapter as AndroidAdapter).notifyItemInserted(androidList.size)
                    recyclerView.scrollToPosition((recyclerView.adapter as AndroidAdapter).itemCount-1)
                    builderCreate.dismiss()
                    showSnackBar("$myNewVersionName Added")
                }
            }


        }

        builder.create()
        builderCreate.show()

    }


    @SuppressLint("SetTextI18n")
    private fun onItemClicked(myData: MyData?, position: Int) {
        with(Dialog(this@RecyclerActivity)) {
            val views: View = layoutInflater.inflate(R.layout.custom_add, null)

            val verName = views.findViewById<EditText>(R.id.txtVersionName)
            val desc = views.findViewById<EditText>(R.id.txtDesc)
            val btnAdd = views.findViewById<Button>(R.id.btnAddData)
            val datePick = views.findViewById<Button>(R.id.datePick)
            val sdkSpinner = views.findViewById<Spinner>(R.id.spinnerSDK)
            val versionSpinner = views.findViewById<Spinner>(R.id.spinnerVersion)

            btnAdd.text = "Update"
            verName.setText(androidList[position].name)
            desc.setText(androidList[position].desc)
            datePick.text = androidList[position].date

            val builder = AlertDialog.Builder(this@RecyclerActivity)
            builder.setView(views)
            val builderCreate = builder.create()

            val itemsVersion = arrayOf(androidList[position].version,"Version: 1", "Version: 1.1","Version: 1.5","Version: 1.6","Version: 2","Version: 2.2","Version: 2.3",
                "Version: 3","Version: 4.0","Version: 4.1","Version: 4.4","Version: 5.0","Version: 6.0","Version: 7.0",
                "Version: 8.0","Version: 9.0","Version: 10","Version: 11","Version: 12"  )

            val adapter = ArrayAdapter(
                this.context,
                android.R.layout.simple_spinner_item, itemsVersion
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            versionSpinner.adapter = adapter

            datePick.setOnClickListener {
                val cal = Calendar.getInstance()
                val day = cal.get(Calendar.DAY_OF_MONTH)
                val month = cal.get(Calendar.MONTH)
                val year = cal.get(Calendar.YEAR)

                val dateListener = DatePickerDialog(this@RecyclerActivity, { _, mYear, mMonth, dayOfMonth ->

                    val month1 = mMonth + 1
                    val myMonth = if (month1 < 10) "0$month1" else {
                        "$month1"
                    }
                    val myDay = if (dayOfMonth < 10) "0$dayOfMonth" else {
                        "$dayOfMonth"
                    }
                    val dateShow = "$myDay-$myMonth-$mYear"
                    datePick.text = dateShow

                }, year, month, day)
                dateListener.show()

            }

            val itemsSDK = arrayOf(androidList[position].sdk,"SDK: 1","SDK: 2","SDK: 3","SDK: 4","SDK: 5","SDK: 8","SDK: 9","SDK: 11",
                "SDK: 14","SDK: 16","SDK: 19","SDK: 21","SDK: 23","SDK: 24","SDK: 26","SDK: 28","SDK: 29","SDK: 30","SDK: 31" )
            val sdkAdapter = ArrayAdapter(
                this.context,
                android.R.layout.simple_spinner_item, itemsSDK
            )
            sdkSpinner.adapter = sdkAdapter
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            views.findViewById<TextView>(R.id.lblVerName).text = "Current Version"
            views.findViewById<TextView>(R.id.lblMinSDK).text = "Current SDK"

            btnAdd.setOnClickListener {
                val myNewVersion: String = versionSpinner.selectedItem.toString()
                val myNewVersionName: String = verName.text.toString()
                val myNewSDK: String = sdkSpinner.selectedItem.toString()
                val myNewDate: String = datePick.text.toString()
                val myNewDesc: String = desc.text.toString()

                when {
                    myNewVersionName.isEmpty() -> {
                        Toast.makeText(this.context,
                            "Version Name is required",
                            Toast.LENGTH_SHORT).show()  // change message as per the field!
                    }
                    myNewVersion== "Update Version" -> {
                        Toast.makeText(this.context,
                            "Select Version",
                            Toast.LENGTH_SHORT).show()  // change message as per the field!
                    }

                    myNewDate.isEmpty() -> {
                        Toast.makeText(this.context,
                            "Provide Date",
                            Toast.LENGTH_SHORT).show()  // change message as per the field!
                    }
                    myNewSDK == "Update SDK" -> {
                        Toast.makeText(this.context,
                            "Select SDK",
                            Toast.LENGTH_SHORT).show()  // change message as per the field!
                    }
                    myNewDesc.isEmpty() -> {
                        Toast.makeText(this.context,
                            "Description should not be empty",
                            Toast.LENGTH_SHORT).show()  // change message as per the field!
                    }
                    else -> {
                        ver = myNewVersionName[0].toString()
                        androidList.removeAt(position)

                        with(androidList) {
                            this.add(position,
                                MyData(ver,
                                    myNewVersionName,
                                    myNewVersion,
                                    myNewDate,
                                    myNewSDK,
                                    myNewDesc)
                            )
                        }
                        (recyclerView.adapter as AndroidAdapter).notifyItemChanged(position)
                        builderCreate.dismiss()
                        showSnackBar("$myNewVersionName is Updated")
                    }
                }

            }
            builder.create()
            builderCreate.show()

        }
    }

    private fun showDeleteAlert(deleteItemPosition: Int) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete Item")
        builder.setMessage("Are you sure to delete this item")

        builder.setPositiveButton("Yes"
        ) { _, _ ->
            val item: String = androidList[deleteItemPosition].name

            androidList.removeAt(deleteItemPosition)
            (recyclerView.adapter as AndroidAdapter).notifyItemRemoved(deleteItemPosition)
            builder.create().dismiss()

            showSnackBar("$item is Deleted")
        }

        builder.setNegativeButton("No"
        ) { _, _ ->

            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            builder.create().dismiss()

        }

            .create()
            .show()
    }



    private fun showSnackBar(snack : String){

        val rv : View = findViewById(R.id.recyclerView)
        Snackbar.make(rv,snack,Snackbar.LENGTH_LONG).show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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
                val iMA = Intent(this, NextActivity::class.java)
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