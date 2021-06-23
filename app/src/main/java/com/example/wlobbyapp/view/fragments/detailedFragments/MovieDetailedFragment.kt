package com.example.wlobbyapp.view.fragments.detailedFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.MovieDetailedFragmentBinding
import com.example.wlobbyapp.model.detailedModels.MovieDetailed.MovieModel
import com.example.wlobbyapp.viewmodel.MovieDetailedViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailedFragment : Fragment() {
    private lateinit var binding: MovieDetailedFragmentBinding
    private var itemData: MovieModel? = null
    private var viewModel: MovieDetailedViewModel = MovieDetailedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detailed_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("movie_id")
        CoroutineScope(Dispatchers.Main).launch {
            itemData = viewModel.getData(id!!)
            setValues()
        }
    }

    private fun setValues() {
        itemData?.let { data ->
            binding.movieDetailedMediaType.text = "movie"
            binding.moviedetailedOverview.text = data.overview
            binding.detailedTitle.text = data.title
            Picasso.get().load(
                ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                    .replace("{id}", data.poster_path.toString())
            )
                .into(binding.movieDetailedPoster)
        }
    }

}