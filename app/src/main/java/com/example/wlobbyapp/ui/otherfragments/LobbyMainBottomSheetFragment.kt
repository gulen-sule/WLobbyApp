package com.example.wlobbyapp.ui.otherfragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.LobbyMainBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream

class LobbyMainBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: LobbyMainBottomSheetBinding
    private lateinit var behavior: BottomSheetBehavior<View>

    var loadImg: (url: String?, name: String) -> Unit = { it1, it2 ->
        val file=File(it1,it2)
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
            binding.bottomSheetImage.setImageBitmap(bitmap)
            binding.bottomSheetImage.visibility = View.VISIBLE}


//        Handler(Looper.getMainLooper()).post {
//            binding.titleTV.text = it1.toString()
//            //
//            binding.bottomSheetImage.setImageResource(R.drawable.ic_calendar_plus)
//
//            val urlPAth = File(requireContext().filesDir, it2)
//            Log.d("imagePathTAG", urlPAth.path.toString())
//
//            requireActivity().runOnUiThread {
//                //Picasso.get().load(urlPAth).networkPolicy(NetworkPolicy.OFFLINE).into(binding.bottomSheetImage)
//                Glide.with(requireActivity()).load(urlPAth).into(binding.bottomSheetImage)
//            }
//
//        }
    }

    lateinit var onCreated: () -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.lobby_main_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*behavior = BottomSheetBehavior.from(binding.bottomSheetlinearLayout)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED*/

        binding.bottomSheetImage.setOnClickListener {
            dismiss()
        }
        onCreated()
    }
}