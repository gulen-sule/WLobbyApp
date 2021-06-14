package com.example.wlobbyapp.data.search.searchKeyword

import com.google.gson.annotations.SerializedName

data class SearchKeywordModel(

    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Results>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)