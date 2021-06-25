package com.example.wlobbyapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wlobbyapp.ui.movieFragment.main.MovieDetailedFragment


class TabsPagerAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
//        when (position) {
//            0 -> {
//                return MovieDetailInfoFragment()
//            }
//            1 -> {
//                return MovieDetailInfoFragment()
//            }
//        }
//        return MovieDetailInfoFragment()
        return MovieDetailedFragment()
    }


}