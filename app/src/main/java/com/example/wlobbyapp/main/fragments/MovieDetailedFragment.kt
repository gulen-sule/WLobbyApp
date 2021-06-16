package com.example.wlobbyapp.main.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.search.multiSearch.Results
import com.example.wlobbyapp.databinding.MovieDetailedFragmentBinding
import com.example.wlobbyapp.main.adapters.SearchAdapter.MediaType
import com.squareup.picasso.Picasso

class MovieDetailedFragment : Fragment() {
    private lateinit var binding: MovieDetailedFragmentBinding
    private var itemData: Results? = null
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detailed_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemData = arguments?.getSerializable("resultData") as Results?
        setValues()

        binding.watchedButton.setOnClickListener {
            val action = MovieDetailedFragmentDirections.actionMovieDetailedFragmentToChooseDateFragment(itemData)
            findNavController().navigate(action)
        }
    }

    private fun setValues() {
        itemData?.let { data ->
            binding.detailedMediaType.text = data.media_type
            when (data.media_type) {
                MediaType.TV.value -> {
                    binding.detailedTitle.text = data.overview
                    binding.detailedTitle.text = data.name
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                            .replace("{id}", data.poster_path.toString())
                    )
                        .into(binding.imageView2)
                }
                MediaType.MOVIE.value -> {
                    binding.detailedTitle.text = data.overview
                    binding.detailedTitle.text = data.title
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                            .replace("{id}", data.poster_path.toString())
                    )
                        .into(binding.imageView2)
                }
                MediaType.PEOPLE.value -> {
                    binding.detailedTitle.text = data.name
                    binding.detailedOverview.text = "no overview"
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/original{id}")
                            .replace("{id}", data.profile_path.toString())
                    )
                        .into(binding.imageView2)
                }
            }
        }
        sharedPref=PreferenceManager.getDefaultSharedPreferences(context)
        if(sharedPref.contains(itemData?.title.toString()))
           // sharedPref.edit().clear().apply()
           binding.watchedDate.text=sharedPref.getString(itemData?.title.toString(),"date")
    }

}