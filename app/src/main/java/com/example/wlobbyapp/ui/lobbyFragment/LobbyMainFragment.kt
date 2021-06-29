package com.example.wlobbyapp.ui.lobbyFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import com.example.wlobbyapp.databinding.FragmentLobbyMainBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LobbyMainFragment : Fragment() {

    private lateinit var binding: FragmentLobbyMainBinding
    private var listData: ArrayList<WatchedEntity>? = arrayListOf()
    private val viewModel: LobbyViewModel by viewModels()
    private var adapter: LobbyRecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lobby_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            listData = viewModel.getAllTodo(requireContext()) as ArrayList<WatchedEntity>?
            adapter = listData?.let { LobbyRecyclerAdapter(it) }
            binding.lobbyRecycler.adapter = adapter
            Log.d("DATA_TAG",Gson().toJson(listData))
        }
    }
}