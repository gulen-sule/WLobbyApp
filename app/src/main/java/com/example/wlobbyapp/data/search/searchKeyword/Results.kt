package com.example.wlobbyapp.data.search.searchKeyword

import com.google.gson.annotations.SerializedName


data class Results(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)