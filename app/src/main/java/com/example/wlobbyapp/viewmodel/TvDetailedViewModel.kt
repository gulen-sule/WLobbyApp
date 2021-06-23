package com.example.wlobbyapp.viewmodel

import com.example.wlobbyapp.model.ApiClient
import com.example.wlobbyapp.model.detailedModels.tvDetailed.TvModel
import com.example.wlobbyapp.model.service.ApiService

class TvDetailedViewModel {
    private var apiService: ApiService? = null
    suspend fun getData(id: Int): TvModel? {
        apiService = ApiClient.getInstance()?.getClient()
        val response = apiService?.tvDetailed(tv_id = id)
        if (response != null) {
            if (response.isSuccessful)
                return response.body()
        }
        return null
    }
}