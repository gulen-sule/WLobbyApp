package com.example.wlobbyapp.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wlobbyapp.Const.moviePhotoPoster
import com.example.wlobbyapp.Const.personPhotoPoster
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.search.multiSearch.Results
import com.example.wlobbyapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class SearchAdapter(diffCallback: DiffUtil.ItemCallback<Results>, val loaded: (Results?) -> Unit) :
    PagingDataAdapter<Results, SearchAdapter.ViewHolder>(diffCallback) {

    inner class ViewHolder(itemMovieView: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieView.root) {
        var eventBinding: ItemMovieBinding = itemMovieView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieItemBinding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return ViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = this.getItem(position)
        holder.eventBinding.movieData = movies

        if (movies != null) {
            if (movies.media_type.toString().compareTo("people") == 0)
                Picasso.get().load(personPhotoPoster.replace("{id}", movies?.poster_path.toString())).into(holder.eventBinding.imageMovie)
            else
                Picasso.get().load(moviePhotoPoster.replace("{id}", movies?.poster_path.toString())).into(holder.eventBinding.imageMovie)
        }

        //objectin const valuelarina direk erisim var bu sekilde o url'in id kismini replace ederek resimleri
        // cekebiliyorum ayrica buyuk versiyonu icin de bir constv olusturuldu

        holder.eventBinding.itemLayout.setOnClickListener {
            loaded(movies)
        }
    }
}