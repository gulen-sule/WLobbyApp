package com.example.wlobbyapp.ui.dateFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.api.models.searchModels.multiSearch.MultiSearchResults
import com.example.wlobbyapp.databinding.FragmentChooseDateBinding
import com.example.wlobbyapp.data.database.room.dao.WatchedItemDao
import com.example.wlobbyapp.data.database.room.WatchedDatabase
import com.example.wlobbyapp.ui.searchUi.adapters.SearchAdapter
import com.squareup.picasso.Picasso

class ChooseDateFragment : Fragment() {
    private lateinit var binding: FragmentChooseDateBinding
    private lateinit var itemDao: WatchedItemDao
    private lateinit var viewModel: ChooseDateViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_date, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemDao = WatchedDatabase.invoke(requireContext().applicationContext).databaseDao()
        viewModel = ChooseDateViewModel(itemDao)

        val itemData = arguments?.getSerializable("resultData") as MultiSearchResults?
        setValuestoItems(itemData)

        val animationCome = AnimationUtils.loadAnimation(requireContext(), R.anim.slidein_bottom)
        val animationGo = AnimationUtils.loadAnimation(requireContext(), R.anim.slideout_top)
        var dateDay = "1"
        var dateMonth = "1"
        var dateYear = "1"

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            dateDay = dayOfMonth.toString()
            dateMonth = month.toString()
            dateYear = year.toString()
            binding.calendarView.isSelected = true
        }
        binding.button.setOnClickListener {
            if (binding.calendarView.isSelected) {
//                val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
//                with(sharedPref?.edit()) {
//                    //Log.d("dataGet ?", dateGet.toString())
//                    this?.putString(itemData?.title.toString(), dateDay + "/" + dateMonth + "/" + dateYear)
//                    this?.apply()
//                }
                viewModel.insertData(
                    date = (dateDay + "/" + dateMonth + "/" + dateYear),
                    title = itemData?.title.toString(),
                    poster = itemData?.poster_path.toString()
                )
                Navigation.findNavController(requireView()).navigateUp()
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

    fun setValuestoItems(itemData: MultiSearchResults?) {
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
                SearchAdapter.MediaType.COLL.value -> {
                    binding.TitleDate.text = data.title
                    Picasso.get().load(
                        ("https://www.themoviedb.org/t/p/w220_and_h330_face{id}")
                            .replace("{id}", data.poster_path.toString())
                    )
                        .into(binding.posterDate)
                }
            }
        }

    }

}