package com.example.wlobbyapp.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wlobbyapp.view.fragments.ChooseDateFragment
import com.example.wlobbyapp.view.fragments.SearchResultsFragment


class TabsPagerAdapter(fm: FragmentManager, var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return SearchResultsFragment()
            }
            1 -> {
                return ChooseDateFragment()
            }
        }
        return SearchResultsFragment()

    }


}