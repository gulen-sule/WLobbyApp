package com.example.wlobbyapp.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.*
import androidx.recyclerview.widget.DiffUtil
import com.example.wlobbyapp.LobbyApp
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.ApiClient
import com.example.wlobbyapp.data.SearchPagingSource
import com.example.wlobbyapp.data.search.multiSearch.MultiSearchModel
import com.example.wlobbyapp.data.search.multiSearch.Results
import com.example.wlobbyapp.data.service.ApiService
import com.example.wlobbyapp.databinding.SearchResultsFragmentBinding
import com.example.wlobbyapp.main.adapters.ExampleLoadStateAdapter
import com.example.wlobbyapp.main.adapters.SearchAdapter
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultsFragment : Fragment() {

    private lateinit var binding: SearchResultsFragmentBinding
    private var retrofit: ApiService? = null
    private lateinit var searchResultData: MultiSearchModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_results_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LobbyApp.getInstance().progressValue.observe(requireActivity(), {
            //observer kullanımı
        })

        val adapter = SearchAdapter(UserComparator, loaded = {
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToChooseDateFragment(it!!)
            Navigation.findNavController(view).navigate(action)
        })

        binding.searchRecylerView.adapter = adapter

        //kullanıcı tokenları shared preferences ile tutulacak 27 mb hafizasi var
        retrofit = ApiClient.getInstance()?.getClient()

        //binding.searchRecylerView.visibility = View.INVISIBLE

        binding.searchButton.setOnClickListener {

            lifecycleScope.launch {
                getPaging().collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }

            /* getMovies(key, completed = {
                 binding.searchRecylerView.visibility = View.VISIBLE

                 adapter.withLoadStateHeaderAndFooter(
                     header = ExampleLoadStateAdapter(adapter::retry),
                     footer = ExampleLoadStateAdapter(adapter::retry)
                 )

                 lifecycleScope.launch {
                     adapter.loadStateFlow.collectLatest {
                         binding.progressBar.isVisible = it.refresh is LoadState.Loading
                     }
                 }

                 adapter.notifyDataSetChanged()
             })*/
        }

        /* */

    }

    private fun getPaging(): Flow<PagingData<Results>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SearchPagingSource(binding.editTextTextPersonName.text.toString(), retrofit!!)
        }.flow
            .cachedIn(requireActivity().lifecycleScope)

    }

    private fun getMovies(key: String, completed: (MultiSearchModel) -> Unit) {
        val response = retrofit?.multiSearch(query = key)

        response?.enqueue(object : Callback<MultiSearchModel> {
            override fun onResponse(call: Call<MultiSearchModel>, response: Response<MultiSearchModel>) {
                Log.d("dataJSON ", Gson().toJson(response.body()))
                response.body()?.let {
                    searchResultData = response.body()!!
                    completed(searchResultData)
                }

            }

            override fun onFailure(call: Call<MultiSearchModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    object UserComparator : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }
    }
}