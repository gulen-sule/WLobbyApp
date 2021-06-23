package com.example.wlobbyapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.DiffUtil
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.SearchResultsFragmentBinding
import com.example.wlobbyapp.model.ApiClient
import com.example.wlobbyapp.model.SearchPagingSource
import com.example.wlobbyapp.model.search.multiSearch.MultiSearchModel
import com.example.wlobbyapp.model.search.multiSearch.MultiSearchResult
import com.example.wlobbyapp.model.service.ApiService
import com.example.wlobbyapp.view.MainActivity
import com.example.wlobbyapp.view.adapters.SearchAdapter
import com.example.wlobbyapp.view.adapters.SearchAdapter.MediaType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchResultsFragment : Fragment() {

    private lateinit var binding: SearchResultsFragmentBinding
    private var apiService: ApiService? = null
    private var isPaged:Boolean=false
    private lateinit var searchResultData: MultiSearchModel
    private var textToSearch: String = "a"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            isPaged=savedInstanceState.getBoolean("is_started_before")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_results_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle_given= activity?.intent?.extras
        if (bundle_given != null) {
            textToSearch = bundle_given.getString("paging_key","a")
        }
//        LobbyApp.getInstance().progressValue.observe(requireActivity(), {
//            //observer kullanımı
//        }) ihtiyacım olursa diye ogren
//          kullanıcı tokenları shared preferences ile tutulacak 27 mb hafizasi var


        val adapter = SearchAdapter(UserComparator, onClickImage = {
            it?.let {
                when (it.media_type) {
                    MediaType.MOVIE.value -> {
                        it.id?.let { movieId ->
                            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToMovieDetailedFragment(movieId)
                            Navigation.findNavController(requireView()).navigate(action)
                        }
                    }
                    MediaType.TV.value -> {
                        it.id?.let { tvId ->
                            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToTvDetailedFragment(tvId)
                            Navigation.findNavController(requireView()).navigate(action)
                        }
                    }
                    MediaType.COLL.value -> {
                    }
                }
            }
        }, onClickButton = { itemData ->
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToChooseDateFragment(itemData)
            findNavController().navigate(action)
        })

        binding.searchRecylerView.adapter = adapter
        apiService = ApiClient.getInstance()?.getClient()
        lifecycleScope.launch {
            getPaging().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        binding.searchButton.setOnClickListener {
            bundle_given?.remove("paging_key")
            textToSearch = binding.editTextTextPersonName.text.toString()
            val bundle = Bundle()
            bundle.putString("paging_key", textToSearch)
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.putExtras( bundle)
            startActivity(intent)
            lifecycleScope.launch {
                getPaging().collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun getPaging(): Flow<PagingData<MultiSearchResult>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SearchPagingSource(textToSearch, apiService!!)
        }.flow
            .cachedIn(requireActivity().lifecycleScope)
    }

//    private fun getMovies(key: String, completed: (MultiSearchModel) -> Unit) {
//        val response = apiService?.multiSearch(query = key)
//
//        response?.enqueue(object : Callback<MultiSearchModel> {
//            override fun onResponse(call: Call<MultiSearchModel>, response: Response<MultiSearchModel>) {
//                Log.d("dataJSON ", Gson().toJson(response.body()))
//                response.body()?.let {
//                    searchResultData = response.body()!!
//                    completed(searchResultData)
//                }
//            }
//
//            override fun onFailure(call: Call<MultiSearchModel>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }

    object UserComparator : DiffUtil.ItemCallback<MultiSearchResult>() {
        override fun areItemsTheSame(oldItem: MultiSearchResult, newItem: MultiSearchResult): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MultiSearchResult, newItem: MultiSearchResult): Boolean {
            return oldItem == newItem
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("is_started_before",isPaged)
    }
}