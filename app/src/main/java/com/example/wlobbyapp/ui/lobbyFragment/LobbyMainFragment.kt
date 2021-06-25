package com.example.wlobbyapp.ui.lobbyFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.FragmentLobbyMainBinding
import com.example.wlobbyapp.databinding.ItemLobbyFavoriteBinding
import com.example.wlobbyapp.model.Room.RoomDao
import com.example.wlobbyapp.model.Room.RoomData
import com.example.wlobbyapp.model.Room.WatchedDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LobbyMainFragment : Fragment() {
    private lateinit var binding: FragmentLobbyMainBinding
    private lateinit var dao: RoomDao
    private var listData: ArrayList<RoomData> = arrayListOf()
    private lateinit var viewModel: LobbyViewModel
    var adapter: LobbyRecyclerAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lobby_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = WatchedDatabase.invoke(requireContext().applicationContext).RoomDao()
        viewModel = LobbyViewModel(dao)
        CoroutineScope(Dispatchers.Main).launch {
            listData = viewModel.getAllTodo() as ArrayList<RoomData>
            Log.d("Data_tag",Gson().toJson(listData))
            adapter?.notifyDataSetChanged()
            adapter = LobbyRecyclerAdapter(listData)
        }
        requireActivity().runOnUiThread {
            binding.lobbyRecycler.adapter = adapter
            adapter?.notifyDataSetChanged()
        }

    }
}