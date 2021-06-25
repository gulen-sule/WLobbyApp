package com.example.wlobbyapp.ui.movieFragment.infoDetailed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.FragmentMovieDetailInfoBinding
import com.example.wlobbyapp.model.detailedModels.MovieDetailed.MovieModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class MovieDetailInfoFragment() : Fragment() {
    private lateinit var binding: FragmentMovieDetailInfoBinding
    private var movieModelObserver :MutableLiveData<MovieModel?> = MutableLiveData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieModelObserver.observe(requireActivity(),{
            it?.let { data ->

                Log.d("dataTAG", Gson().toJson(data))

                binding.movieDetailedMediaType.text = "movie"
                binding.moviedetailedOverview.text = data.overview
                binding.detailedTitle.text = data.title
                Picasso.get().load(
                    ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                        .replace("{id}", data.poster_path.toString())
                )
                    .into(binding.movieDetailedPoster)
            }
        })
    }

     fun setValues(itemData: MovieModel?) {
        movieModelObserver.postValue(itemData)
    }

}