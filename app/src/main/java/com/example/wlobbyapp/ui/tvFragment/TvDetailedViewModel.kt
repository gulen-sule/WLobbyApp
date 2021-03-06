package com.example.wlobbyapp.ui.tvFragment

import com.example.wlobbyapp.data.api.service.ApiClient
import com.example.wlobbyapp.data.api.models.detailedModels.tvDetailed.TvModel
import com.example.wlobbyapp.data.api.service.ApiService

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