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
import com.example.wlobbyapp.databinding.ItemPersonBinding
import com.example.wlobbyapp.databinding.ItemTvBinding
import com.example.wlobbyapp.main.adapters.SearchAdapter.MediaType.*
import com.squareup.picasso.Picasso

class SearchAdapter(diffCallback: DiffUtil.ItemCallback<Results>, val onClick: (Results?) -> Unit) :
    PagingDataAdapter<Results, SearchAdapter.ViewHolder>(diffCallback) {
    private var layoutType: Int = 0

    inner class ViewHolder : RecyclerView.ViewHolder {
        lateinit var itemMovieBinding: ItemMovieBinding
        lateinit var itemTvBinding: ItemTvBinding
        lateinit var itemPersonBinding: ItemPersonBinding

        constructor(itemPersonBinding: ItemPersonBinding) : super(itemPersonBinding.root) {
            this.itemPersonBinding = itemPersonBinding

        }

        constructor(itemTvBinding: ItemTvBinding) : super(itemTvBinding.root) {
            this.itemTvBinding = itemTvBinding
        }

        constructor(itemMovieBinding: ItemMovieBinding) : super(itemMovieBinding.root) {
            this.itemMovieBinding = itemMovieBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (layoutType) {
            0 -> ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tv, parent, false) as ItemTvBinding)
            1 -> ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false) as ItemMovieBinding)
            else -> ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_person, parent, false) as ItemPersonBinding)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = this.getItem(position)

        when (movies?.media_type) {
            MOVIE.value -> {
                holder.itemMovieBinding.movieData = movies
                if(movies.poster_path!=null)
                Picasso.get().load(moviePhotoPoster.replace("{id}", movies.poster_path.toString())).into(holder.itemMovieBinding.imageMovie)
                //objectin const valuelarina direk erisim var bu sekilde o url'in id kismini replace ederek resimleri
                // cekebiliyorum ayrica buyuk versiyonu icin de bir constv olusturuldu
                holder.itemMovieBinding.itemLayout.setOnClickListener {
                    onClick(movies)
                }
            }
            TV.value -> {
                holder.itemTvBinding.movieData = movies
                if(movies.poster_path!=null)
                Picasso.get().load(moviePhotoPoster.replace("{id}", movies.poster_path.toString())).into(holder.itemTvBinding.imageMovie)
                holder.itemTvBinding.movieTitle.text = movies.name
                holder.itemTvBinding.itemLayout.setOnClickListener {
                    onClick(movies)
                }
            }
            PEOPLE.value -> {
                holder.itemPersonBinding.movieData = movies
                if(movies.profile_path!=null)
                Picasso.get().load(personPhotoPoster.replace("{id}", movies.profile_path.toString())).into(holder.itemPersonBinding.imageMovie)
                holder.itemPersonBinding.movieTitle.text = movies.name
                holder.itemPersonBinding.itemLayout.setOnClickListener {
                    onClick(movies)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (getItem(position)?.media_type.toString()) {
            TV.value -> {
                layoutType = 0
            }
            MOVIE.value -> {
                layoutType = 1
            }
            PEOPLE.value -> {
                layoutType = 2
            }
        }
        return layoutType
    }

    enum class MediaType(val value: String) {
        TV("tv"),
        MOVIE("movie"),
        PEOPLE("person")
    }


}