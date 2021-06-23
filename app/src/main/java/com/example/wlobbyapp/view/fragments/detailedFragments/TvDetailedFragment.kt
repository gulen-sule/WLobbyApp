package com.example.wlobbyapp.view.fragments.detailedFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wlobbyapp.Const.moviePhotoPoster
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.MovieDetailedFragmentBinding
import com.example.wlobbyapp.databinding.TvDetailedFragmentBinding
import com.example.wlobbyapp.model.detailedModels.MovieDetailed.MovieModel
import com.example.wlobbyapp.model.detailedModels.tvDetailed.TvModel
import com.example.wlobbyapp.viewmodel.MovieDetailedViewModel
import com.example.wlobbyapp.viewmodel.TvDetailedViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvDetailedFragment : Fragment() {
    private lateinit var binding: TvDetailedFragmentBinding
    private var itemData: TvModel? = null
    private var viewModel: TvDetailedViewModel = TvDetailedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.tv_detailed_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Int? = arguments?.getInt("tv_id")
        Log.d("StatusCode here:",id.toString())
        CoroutineScope(Dispatchers.Main).launch {
                itemData = viewModel.getData(id!!)

                setValues()


        }
    }

    private fun setValues() {
        itemData?.let { data ->
            binding.tvDetailedMediaType.text = "tv"
            binding.tvDetailedOverview.text = data.overview
            binding.tvDetailedTitle.text = data.name
            Picasso.get().load(
                moviePhotoPoster
                    .replace("{id}", data.poster_path.toString())
            )
                .into(binding.tvDetailedPoster)
        }
    }
}