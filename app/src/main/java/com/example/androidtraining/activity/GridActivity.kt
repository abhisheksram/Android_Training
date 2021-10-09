package com.example.androidtraining.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.adapters.GridAdapter
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.view_models.MyViewModel


class GridActivity : AppCompatActivity() {

    lateinit var adapter: GridAdapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerView : RecyclerView
    private lateinit var gridLayoutManager : GridLayoutManager

    private lateinit var viewModelFactory: MyViewModel.ViewModelFactory
    private val myViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val actionBar = supportActionBar!!
        actionBar.title = "Images"

        viewModelFactory = MyViewModel.ViewModelFactory()

        getImagesObserver()

        gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 0) 2 else 1
            }
        }
        recyclerView  = findViewById(R.id.rvGrid)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = GridAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayoutManager

    }

    private fun getImagesObserver() {
        myViewModel.getImages()
        myViewModel.imageLiveData.observe(this,  {
            if (it != null) {
                adapter.images = it.toMutableList()

            }

        })
        myViewModel.errorMessage.observe(this,  {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

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