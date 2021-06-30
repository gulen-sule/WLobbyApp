package com.example.wlobbyapp.ui.otherfragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.LobbyMainBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import java.io.File

class LobbyMainBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: LobbyMainBottomSheetBinding
    private lateinit var behavior: BottomSheetBehavior<View>

    var loadImg: (url: String?, name: String) -> Unit = { it1, it2 ->
        Log.d("imagePathTAG", it1.toString())
        //binding.bottomSheetImage.setImageBitmap(it)

        Handler(Looper.getMainLooper()).post {
            binding.titleTV.text = it1.toString()
            //binding.bottomSheetImage.visibility = View.VISIBLE
            binding.bottomSheetImage.setImageResource(R.drawable.ic_calendar_plus)

            val urlPAth = File(requireContext().filesDir, it2)
            Log.d("imagePathTAG", urlPAth.path.toString())


            //Picasso.get().load(File(requireContext().filesDir, it2)).into(binding.bottomSheetImage)
        }
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