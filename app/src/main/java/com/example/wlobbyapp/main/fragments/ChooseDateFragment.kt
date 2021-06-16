package com.example.wlobbyapp.main.fragments

import android.content.Context
import android.icu.util.TimeZone
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CalendarView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.navigateUp
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.search.multiSearch.Results
import com.example.wlobbyapp.databinding.ChooseDateFragmentBinding
import com.example.wlobbyapp.main.adapters.SearchAdapter
import com.squareup.picasso.Picasso
import okhttp3.internal.wait

class ChooseDateFragment : Fragment() {
    private lateinit var binding: ChooseDateFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.choose_date_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemData = arguments?.getSerializable("movieData") as Results
        setValuestoItems(itemData)

        val animationCome = AnimationUtils.loadAnimation(requireContext(), R.anim.slidein_bottom)
        val animationGo = AnimationUtils.loadAnimation(requireContext(), R.anim.slideout_top)
        val dateToday: Long = binding.calendarView.date
        var dateGet: Long = 0L

        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                if (binding.calendarView.date > dateToday)
                    dateGet = binding.calendarView.date
                binding.calendarView.isSelected = true
            }

        )
        binding.button.setOnClickListener {
            if (binding.calendarView.isSelected) {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                with(sharedPref?.edit()) {
                    this?.putLong(itemData.title.toString(), dateGet)
                    this?.apply()
                }
                Navigation.findNavController(view).navigateUp()
            }
            binding.warningChooseDate.startAnimation(animationCome)
        }

        animationCome.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.warningChooseDate.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {

                Handler(Looper.getMainLooper()).postDelayed({
                    animation?.cancel()
                    binding.warningChooseDate.clearAnimation()
                    binding.warningChooseDate.startAnimation(animationGo)
                }, 1000)

            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        animationGo.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.warningChooseDate.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.warningChooseDate.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {
                TODO("Not yet implemented")
            }


        })
    }

    fun setValuestoItems(itemData: Results?) {
        itemData?.let { data ->
            binding.mediaTypeDate.text = data.media_type
            when (data.media_type) {
                SearchAdapter.MediaType.TV.value -> {
                    binding.TitleDate.text = data.name
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                            .replace("{id}", data.poster_path.toString())
                    )
                        .into(binding.posterDate)
                }
                SearchAdapter.MediaType.MOVIE.value -> {
                    binding.TitleDate.text = data.title
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                            .replace("{id}", data.poster_path.toString())
                    )
                        .into(binding.posterDate)
                }
                SearchAdapter.MediaType.PEOPLE.value -> {
                    binding.TitleDate.text = data.name
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/original{id}")
                            .replace("{id}", data.profile_path.toString())
                    )
                        .into(binding.posterDate)
                }
            }
        }

    }

}