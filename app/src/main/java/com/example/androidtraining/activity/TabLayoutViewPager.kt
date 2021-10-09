package com.example.androidtraining.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.androidtraining.*
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.fragments.TabFragment1
import com.example.androidtraining.fragments.TabFragment2
import com.example.androidtraining.fragments.TabFragment3
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutViewPager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_view_pager)

        val actionBar = supportActionBar!!
        actionBar.title = "Tab Layout with ViewPager"

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.tabViewPager)

        val tabAdapter = TabAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = tabAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.chats)
                }
                1 -> {
                    tab.text = getString(R.string.status)
                }
                else -> tab.text = getString(R.string.calls)
            }
        }.attach()
    }

    inner class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val totalTabs = 3

        override fun getItemCount(): Int {
            return totalTabs
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    TabFragment1()
                }
                1 -> {
                    TabFragment2()
                }

                else -> TabFragment3()

            }
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