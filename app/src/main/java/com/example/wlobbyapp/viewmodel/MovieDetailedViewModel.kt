package com.example.wlobbyapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wlobbyapp.model.ApiClient
import com.example.wlobbyapp.model.detailedModels.MovieDetailed.MovieModel
import com.example.wlobbyapp.model.service.ApiService

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