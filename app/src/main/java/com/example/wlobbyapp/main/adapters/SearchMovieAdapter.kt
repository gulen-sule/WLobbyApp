package com.example.wlobbyapp.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.search.searchMovieModel.SearchModel
import com.example.wlobbyapp.databinding.ItemMovieBinding

class SearchMovieAdapter(private val searchResult: SearchModel) : RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {
    inner class ViewHolder(itemMovieView: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieView.root) {
        var eventBinding: ItemMovieBinding = itemMovieView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieItemBinding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return ViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return searchResult.results.size
    }

}