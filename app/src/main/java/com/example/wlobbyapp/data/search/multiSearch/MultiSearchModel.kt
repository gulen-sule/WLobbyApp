package com.example.wlobbyapp.data.search.multiSearch

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiSearchModel(

    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") var results: List<Results>? = null,
    @SerializedName("total_pages") val total_pages: Int? = null,
    @SerializedName("total_results") val total_results: Int? = null
) : Serializable