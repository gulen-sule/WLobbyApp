package com.example.wlobbyapp.data.search.multiSearch

import com.google.gson.annotations.SerializedName

data class MultiSearchModel(

    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<Results>? = null,
    @SerializedName("total_pages") val total_pages: Int? = null,
    @SerializedName("total_results") val total_results: Int? = null
)