package com.example.wlobbyapp.ui.searchUi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.*
import androidx.recyclerview.widget.DiffUtil
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.FragmentSearchResultsBinding
import com.example.wlobbyapp.data.api.service.ApiClient
import com.example.wlobbyapp.data.api.models.search.multiSearch.MultiSearchResults
import com.example.wlobbyapp.data.api.service.ApiService
import com.example.wlobbyapp.ui.MainActivity
import com.example.wlobbyapp.ui.searchUi.adapters.SearchAdapter
import com.example.wlobbyapp.ui.searchUi.adapters.SearchAdapter.MediaType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchResultsFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultsBinding
    private var apiService: ApiService? = null
    private var isPaged: Boolean = false
    private var textToSearch: String = "One"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            isPaged = savedInstanceState.getBoolean("is_started_before")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_results, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle_given = this.arguments?.getBundle("key_text_bundle")
        if (bundle_given != null) {
            textToSearch = bundle_given.getString("paging_key", "One")
        }
//        LobbyApp.getInstance().progressValue.observe(requireActivity(), {
//            //observer kullanımı
//        }) ihtiyacım olursa diye ogren
//          kullanıcı tokenları shared preferences ile tutulacak 27 mb hafizasi var

        val adapter = SearchAdapter(UserComparator, onClickImage = {
            binding.progressBar.visibility = View.VISIBLE
            it?.let {
                when (it.media_type) {
                    MediaType.MOVIE.value -> {
                        it.id?.let { movieId ->
                            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToMovieDetailedFragment(movieId)
                            getMainActivity()?.navController?.navigate(action)
                            //Navigation.findNavController(requireView()).navigate(action)
                        }
                    }
                    MediaType.TV.value -> {
                        it.id?.let { tvId ->
                            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToTvDetailedFragment(tvId)
                            (requireActivity() as MainActivity).navController.navigate(action)

                        }
                    }
                    MediaType.COLL.value -> {
                    }
                }
            }
        }, onClickButton = { itemData ->
            binding.progressBar.visibility = View.VISIBLE
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToChooseDateFragment(itemData)
            Log.d("navController content", findNavController().toString())
            findNavController().navigate(action)

        })

        binding.searchRecylerView.adapter = adapter
        apiService = ApiClient.getInstance()?.getClient()
        lifecycleScope.launch {
            getPaging().collectLatest { pagingData ->
                binding.progressBar.visibility = View.GONE
                adapter.submitData(pagingData)
            }
        }
        binding.searchButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            bundle_given?.remove("paging_key")
            textToSearch = binding.editTextTextPersonName.text.toString()
            val bundle = Bundle()
            bundle.putString("paging_key", textToSearch)
            arguments?.putBundle("key_text_bundle", bundle)

            lifecycleScope.launch {
                getPaging().collectLatest { pagingData ->
                    binding.progressBar.visibility = View.GONE
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun getPaging(): Flow<PagingData<MultiSearchResults>> {
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

    object UserComparator : DiffUtil.ItemCallback<MultiSearchResults>() {
        override fun areItemsTheSame(oldItem: MultiSearchResults, newItem: MultiSearchResults): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MultiSearchResults, newItem: MultiSearchResults): Boolean {
            return oldItem == newItem
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("is_started_before", isPaged)
    }

    fun getMainActivity(): MainActivity? {
        if ((requireActivity() is MainActivity)) {
            return requireActivity() as MainActivity
        }
        return null
    }
}