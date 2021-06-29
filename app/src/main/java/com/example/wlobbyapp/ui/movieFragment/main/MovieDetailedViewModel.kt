package com.example.wlobbyapp.ui.movieFragment.main

import androidx.lifecycle.ViewModel
import com.example.wlobbyapp.data.api.service.ApiClient
import com.example.wlobbyapp.data.api.models.detailedModels.MovieDetailed.MovieModel
import com.example.wlobbyapp.data.api.service.ApiService

class MovieDetailedViewModel : ViewModel() {
    private var apiService: ApiService? = null
    suspend fun getData(id: Int): MovieModel? {

        apiService = ApiClient.getInstance()?.getClient()
        val response = apiService?.movieDetailed(movie_id = id)
        if (response != null) {
            if (response.isSuccessful)
                return response.body()
        }
        return null
    }

}