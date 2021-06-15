package com.example.wlobbyapp.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.SearchResultsFragmentBinding
import com.example.wlobbyapp.databinding.SearchResultsFragmentBindingImpl
import com.example.wlobbyapp.main.fragments.SearchResultsFragment

class LoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_movie, parent, false)
) {
    private val binding = SearchResultsFragmentBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.progressBar

        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState) {

        progressBar.isVisible = loadState is LoadState.Loading
    }
}
class ExampleLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(parent, retry)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}