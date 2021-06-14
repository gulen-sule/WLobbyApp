package com.example.wlobbyapp.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wlobbyapp.LobbyApp
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.ApiClient
import com.example.wlobbyapp.data.search.multiSearch.MultiSearchModel
import com.example.wlobbyapp.data.service.ApiService
import com.example.wlobbyapp.databinding.SearchResultsFragmentBinding
import com.example.wlobbyapp.main.adapters.SearchAdapter
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

        LobbyApp.getInstance().progressValue.observe(requireActivity(), {//observer kullanımı
        })

        retrofit = ApiClient.getInstance()?.getClient()
        binding.searchRecylerView.visibility = View.INVISIBLE
        binding.searchButton.setOnClickListener {
            val key = binding.editTextTextPersonName.text.toString()
            getMovies(key,completed = {
                binding.searchRecylerView.visibility = View.VISIBLE
                val adapter = SearchAdapter(it)
                binding.searchRecylerView.adapter = adapter
            })
        }
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
}