package com.example.androidtraining.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.androidtraining.R
import com.example.androidtraining.fragments.*


private const val NUM_PAGES = 6
private lateinit var viewPager: ViewPager2

class ViewPagerSliderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_slider)

        val actionBar = supportActionBar!!
        actionBar.title = "Slide Activity"

        viewPager = findViewById(R.id.sliderViewPager)

        val slideAdapter = SlideAdapter(this)
        viewPager.adapter = slideAdapter



        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            private var currentPage : Int = 0
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentPage = position
            }


            override fun onPageScrollStateChanged(state: Int) {
                if ( state == 1 && currentPage == NUM_PAGES -1) {

                    currentPage = 0

                    viewPager.currentItem = currentPage

                }

            }
        })

    }


    private inner class SlideAdapter(fragmentAdapter: FragmentActivity) : FragmentStateAdapter(fragmentAdapter) {


        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment{

            return when (position) {
                0 -> {
                    TabFragment1()
                }
                1 -> {
                    TabFragment2()
                }
                2 -> {
                    TabFragment3()
                }
                3 -> {
                    FirstFragment()
                }
                4 -> {
                    SecondFragment()
                }
                else -> {
                    ThirdFragment()
                }
            }
        }
    }
}