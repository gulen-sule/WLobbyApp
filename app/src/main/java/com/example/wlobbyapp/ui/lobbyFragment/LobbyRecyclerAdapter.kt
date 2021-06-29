package com.example.wlobbyapp.ui.lobbyFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import com.example.wlobbyapp.databinding.ItemLobbyFavoriteBinding
import com.squareup.picasso.Picasso

class LobbyRecyclerAdapter(private var data: List<WatchedEntity>) : RecyclerView.Adapter<LobbyRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(todoView: ItemLobbyFavoriteBinding) : RecyclerView.ViewHolder(todoView.root) {
        var eventbinding: ItemLobbyFavoriteBinding = todoView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: ItemLobbyFavoriteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_lobby_favorite, parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val watched = data[position]
        holder.eventbinding.itemNameTitle.text = watched.itemTitle
        Picasso.get().load(
            ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                .replace("{id}", watched.photoPath.toString())
        )
            .into(holder.eventbinding.itemPhotoPoster)
        holder.eventbinding.itemDate.text = watched.date

        holder.eventbinding.rootCL.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
       return data.size
    }
}