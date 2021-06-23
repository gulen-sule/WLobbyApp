package com.example.wlobbyapp.model.search.searchCollection

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectionSearchModel(
    @SerializedName("status_code") val status_code: Int?,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") var results: List<CollectionSearchResult>? = null,
    @SerializedName("total_pages") val total_pages: Int? = null,
    @SerializedName("total_results") var total_results: Int? = null,
    ):Serializable
