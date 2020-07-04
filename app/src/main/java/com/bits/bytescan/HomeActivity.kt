package com.bits.bytescan

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    val fragment1 = SliderFragment()
    val fragment2 = SliderFragment()
    val fragment3 = SliderFragment()
    lateinit var adapter: mypagerAdapter
    lateinit var activity: Activity

    lateinit var preference : SharedPreferences
    val pref_show_intro = "Intro"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragment1.setTitle("Welcome to Byte Scan")
        fragment2.setTitle("MADE IN INDIA")
        fragment3.setTitle("SCAN, MAKE PDF, SHARE")

        adapter = mypagerAdapter(supportFragmentManager)
        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)
        activity = this
        preference = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)
        if (!preference.getBoolean(pref_show_intro,true)){
            startActivity(Intent(activity,Register::class.java))
            finish()

        }



        view_pager.adapter = adapter
        btn_next.setOnClickListener {
            view_pager.currentItem++
        }

        btn_skip.setOnClickListener {
            goToDashBoard()
        }

        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == adapter.list.size - 1) {
                    btn_next.text = "REGISTER"
                    btn_next.setOnClickListener {
                        goToDashBoard()
                    }

                } else {
                    btn_next.text = "NEXT"
                    btn_next.setOnClickListener {
                        view_pager.currentItem++
                    }

                }
                when(view_pager.currentItem){
                    0->{
                        indicator1.setTextColor(Color.WHITE)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)


                    }
                    1->{
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.WHITE)
                        indicator3.setTextColor(Color.GRAY)


                    }
                    2->{
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.WHITE)


                    }
                }

            }

        })



    }
    fun goToDashBoard(){
        startActivity(Intent(activity,Register::class.java))
        finish()
        val editor = preference.edit()
        editor.putBoolean(pref_show_intro,false)
        editor.apply()
    }



    class mypagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){

        val list: MutableList<Fragment> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return list[position]
        }

        override fun getCount(): Int {
            return list.size
        }

    }
}