package com.example.wlobbyapp.data.api.models.search.multiSearch

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiSearchModel(
    @SerializedName("status_code") val status_code: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") var results: List<MultiSearchResults>? = null,
    @SerializedName("total_pages") val total_pages: Int? = null,
    @SerializedName("total_results") val total_results: Int? = null
) : Serializable

