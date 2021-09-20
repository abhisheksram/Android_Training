package com.example.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var ver : Array<MyData>
    lateinit var name : Array<MyData>
    lateinit var version : Array<MyData>
    lateinit var sdk : Array<MyData>
    lateinit var desc : Array<MyData>
    lateinit var date : Array<MyData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        var androidList = ArrayList<MyData>()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = AndroidAdapter(androidList)

        androidList.add(MyData("A", "Alpha", "Version: 1","September 23, 2008", "SDK: 1",
            "Android 1.0, the first commercial version of the software"))
        androidList.add(MyData(
            "B", "Beta", "Version: 1.1", "February 9, 2009", "SDK: 2",
            "Introduced features like support for third-party keyboards, video recording, video playback, and copy-paste feature for the web browser."))
        androidList.add(MyData(                "C", "CupCake", "Version: 1.5", "April 27, 2009", "SDK: 3",
                "It is the first Android version to officially get a dessert nickname. Android 1.5 was known as Android Cupcake and it was developed by Google with API 3.")        )

        androidList.add(MyData(
                "D", "Donut", "Version: 1.6", "September 15, 2009", "SDK: 4",
                "It introduced some distinctive features such as support for CDMA technology, support for different screen sizes, " +
                        "and a battery usage indicator that was also first introduced on this version."
            )
        )
        androidList.add(MyData(
                "E", "Eclair", "Version: 2", "October 27, 2009", "SDK: 5",
                "Eclair offered features like an improved UI with a Google search bar on the top and it also supported HTML 5"
            )
        )
        androidList.add(MyData(
                "F", "Froyo", "Version: 2.2", "May 20, 2010", "SDK: 8",
                "This version was unveiled during Google I/O 2010 conference and it offered several optimizations"
            )
        )
        androidList.add(MyData(
                "G", "Gingerbread", "Version: 2.2", "December 6, 2010", "SDK: 9",
                "This version introduced features like NFC support and VoIP calls."
            )
        )

        androidList.add(MyData(
                "H", "Honeycomb", "Version: 3.0", "February 22, 2011", "SDK: 11",
                "This version of Android was designed for large screen devices like tablets"
            )
        )

        androidList.add(MyData(
                "I", "Ice Cream Sandwich", "Version: 4.0", "October 18, 2011", "SDK: 14",
                "This version was designed to offer a unified experience for both smartphones and tablets. " +
                        "It was also the first Android version to support Face Unlock on select devices."
            )
        )

        androidList.add(MyData(
                "J", "Jelly Bean", "Version: 4.1", "July 9, 2012", "SDK: 16",
                "Android Jelly Bean is also officially the 10th iteration of Android and it was developed to offer performance " +
                        "improvements along with smooth user experience when compared to Android 4.0"
            )
        )

        androidList.add(MyData(
                "K", "KitKat", "Version: 4.4", "October 31, 2013", "SDK: 19",
                "This version of Android was developed to offer an improved user experience on devices with limited hardware capabilities."
            )
        )

        androidList.add(MyData(
                "L", "Lollypop", "Version: 5.0", "November 4, 2014", "SDK: 21",
                "This version of Android offered a redesigned UI and it also replaced Dalvik with ART or " +
                        "Android Runtime to improve application performance and battery optimization."
            )
        )

        androidList.add(MyData(
                "M", "Marshmallow", "Version: 6.0", "October 2, 2015", "SDK: 23",
                "It offered a new permission architecture to improve user-privacy and " +
                        "it also natively supported USB Type-C port and a physical fingerprint sensor"
            )
        )

        androidList.add(MyData(
                "N", "Nougat", "Version: 7.0", "August 22, 2016", "SDK: 24",
                "Nougat offered support for Vulkan API for better graphics rendering along with a new app notification format."
            )
        )

        androidList.add(MyData(
                "O", "Oreo", "Version: 8.0", "August 21, 2017", "SDK: 26",
                "Oreo was the first Android version to support Bluetooth 5.0 and wide color gamut."
            )
        )

        androidList.add(MyData(
                "P", "Pie", "Version: 9", "August 6, 2018", "SDK: 28",
                "It offered a refreshed material design with a new style of navigation buttons." +
                        " Till today, a lot of smartphones are still based on Android 9 Pie."
            )
        )

        androidList.add(MyData(
                "Q", "Quince Tart", "Version: 10", "September 3, 2019", "SDK: 29",
                "This version was known as Android Q at the time of development and this is the first modern Android OS that doesn't have a dessert code name. " +
                        "It offered a complete full-screen user-interface with a redesigned navigation system, which is a bit similar to the modern iPhones."
            )
        )

        androidList.add(MyData(
                "R", "Red Velvet Cake", "Version: 11", "September 8, 2020", "SDK: 30",
                "It comes with features like conversation notifications and this is also the first official Android version to offer a built-in screen recorder"
            )
        )

        androidList.add(MyData(
                "S", "Snow Cone", "Version: 12", "Yet to be released", "SDK: 31",
                "Not Available"
            )
        )

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_widget -> {
                val iMA = Intent(this, NextActivity::class.java)
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