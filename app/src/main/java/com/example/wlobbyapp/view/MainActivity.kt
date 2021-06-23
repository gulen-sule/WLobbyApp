package com.example.wlobbyapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.wlobbyapp.LobbyApp
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.MainActivityBinding
import com.example.wlobbyapp.view.adapters.TabsPagerAdapter
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var itemSearch: TabItem
    private lateinit var itemCreate: TabItem
    private lateinit var itemLobby: TabItem
    private lateinit var itemProfıle: TabItem


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

//        val tabLayout=findViewById<TabLayout>(R.id.tabLayout)
//        val viewPager=findViewById<ViewPager>(R.id.viewPager)
//        viewPager.adapter=TabsPagerAdapter(this.supportFragmentManager,tabLayout.tabCount)
//        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//
//        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab != null) {
//                    viewPager.currentItem=tab.position
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//
//
//        })
    }


}