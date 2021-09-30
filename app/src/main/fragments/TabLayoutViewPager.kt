package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.fragments.fragments.TabFragment1
import com.example.fragments.fragments.TabFragment2
import com.example.fragments.fragments.TabFragment3
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
}