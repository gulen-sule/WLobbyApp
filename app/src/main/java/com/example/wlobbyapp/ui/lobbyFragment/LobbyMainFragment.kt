package com.example.wlobbyapp.ui.lobbyFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Picture
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.database.room.data.WatchedEntity
import com.example.wlobbyapp.data.event.LobbyImageEvent
import com.example.wlobbyapp.data.event.LobbyImageLoadedEvent
import com.example.wlobbyapp.databinding.FragmentLobbyMainBinding
import com.example.wlobbyapp.ui.MainActivity
import com.example.wlobbyapp.ui.otherfragments.LobbyMainBottomSheetFragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@AndroidEntryPoint
class LobbyMainFragment : Fragment() {

    private lateinit var binding: FragmentLobbyMainBinding
    private var listData: ArrayList<WatchedEntity>? = arrayListOf()
    private val viewModel: LobbyViewModel by viewModels()
    private var adapter: LobbyRecyclerAdapter? = null
    private var eventBus: EventBus = EventBus.getDefault()
    lateinit var bottomSheet: LobbyMainBottomSheetFragment
    private var savedInstanceState: Bundle? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.savedInstanceState = savedInstanceState
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lobby_main, container, false)
        //bottomSheet.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //requireContext().startService(Intent(requireContext(), UploadService::class.java))

        CoroutineScope(Dispatchers.Main).launch {
            listData = viewModel.getAllTodo(requireContext()) as ArrayList<WatchedEntity>?
            adapter = listData?.let {
                LobbyRecyclerAdapter(it) { Url ->
                    Log.d("tryTag", eventBus.toString())
                    eventBus.post(LobbyImageEvent(Url))
                }
            }
            binding.lobbyRecycler.adapter = adapter
            Log.d("DATA_TAG", Gson().toJson(listData))
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("onAttachTAG", "ok")
        eventBus.register(this)
    }

    override fun onStop() {
        eventBus.unregister(this)
        bottomSheet.onStop()
        Log.d("onStopTAG", "ok")
        super.onStop()
    }

    override fun onDestroy() {
        bottomSheet.onDestroy()
        super.onDestroy()
    }

    @Subscribe
    fun imageLoaded(event: Any) {
        when (event) {
            is LobbyImageLoadedEvent -> {
                Toast.makeText(context, "Image is loaded", Toast.LENGTH_SHORT).show()
                Log.d("imageTAG", event.pathOfImage)
                //val bitmap = BitmapFactory.decodeFile(event.pathOfImage)
                //bottomSheet.onViewCreated(requireView(), savedInstanceState)
                bottomSheet = LobbyMainBottomSheetFragment()
                bottomSheet.show(childFragmentManager, "bottomSheetFragment")

                bottomSheet.onCreated = {
                    bottomSheet.loadImg(event.pathOfImage,event.name)
                }
            }
        }
    }
}