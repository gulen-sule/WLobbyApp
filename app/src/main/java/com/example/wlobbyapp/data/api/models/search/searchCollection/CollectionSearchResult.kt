package com.example.wlobbyapp.data.api.models.search.searchCollection

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectionSearchResult(

    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") var poster_path: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("backdrop_path") var backdrop_path: String? = null

) : Serializable