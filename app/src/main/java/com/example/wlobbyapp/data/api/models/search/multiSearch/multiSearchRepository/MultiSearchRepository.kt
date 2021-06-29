package com.example.wlobbyapp.data.api.models.search.multiSearch.multiSearchRepository

import com.example.wlobbyapp.data.api.service.ApiService

interface MultiSearchRepository {
    fun getClient():ApiService
}