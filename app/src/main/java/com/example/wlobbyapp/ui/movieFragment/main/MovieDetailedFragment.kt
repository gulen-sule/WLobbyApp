package com.example.wlobbyapp.ui.movieFragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.FragmentMovieDetailedBinding
import com.example.wlobbyapp.model.detailedModels.MovieDetailed.MovieModel
import com.example.wlobbyapp.ui.fragments.ChooseOtherThingsFragment
import com.example.wlobbyapp.ui.movieFragment.infoDetailed.MovieDetailInfoFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailedFragment : Fragment() {

    private lateinit var movieDetailInfoFragment: MovieDetailInfoFragment
    private lateinit var binding: FragmentMovieDetailedBinding
    private var viewModel: MovieDetailedViewModel = MovieDetailedViewModel()
    private var itemData: MovieModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detailed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("movie_id")

        movieDetailInfoFragment = MovieDetailInfoFragment()


        CoroutineScope(Dispatchers.Main).launch {
            if (id != null) {
                itemData = viewModel.getData(id)
            }

            movieDetailInfoFragment.setValues(itemData)
            binding.viewPager.adapter = TabsPagerAdapter(childFragmentManager, binding.tabLayout.tabCount)
        }

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    inner class TabsPagerAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return totalTabs
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return movieDetailInfoFragment
                }
                1 -> {
                    return ChooseOtherThingsFragment()
                }
            }
            return movieDetailInfoFragment
        }


    }

}