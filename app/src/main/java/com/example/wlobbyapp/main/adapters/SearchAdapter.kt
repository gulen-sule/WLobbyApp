package com.example.wlobbyapp.main.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wlobbyapp.Const.moviePhotoPoster
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.search.multiSearch.MultiSearchModel
import com.example.wlobbyapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class SearchAdapter(private val searchResult: MultiSearchModel) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val BASE_URL = "https://api.themoviedb.org/3/search/multi"

    inner class ViewHolder(itemMovieView: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieView.root) {
        var eventBinding: ItemMovieBinding = itemMovieView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieItemBinding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return ViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = searchResult.results?.get(position)
        holder.eventBinding.movieData = movies
        Log.d("searchAdapterPhotoTAG", moviePhotoPoster.replace("{id}", movies?.backdrop_path.toString()))
        Picasso.get().load(moviePhotoPoster.replace("{id}", movies?.poster_path.toString())).into(holder.eventBinding.imageMovie)
        //objectin const valuelarina direk erisim var bu sekilde o url!in id kismini replace ederek resimleri
        // cekebiliyorum ayrica buyuk versiyonu icin de bir constv olusturuldu
    }

    override fun getItemCount(): Int {
        searchResult.results?.let {
            return searchResult.results.size
        }
        return 0
    }


}