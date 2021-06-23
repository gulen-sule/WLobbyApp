package com.example.wlobbyapp.model.detailedModels.CollectionDetailed

import com.google.gson.annotations.SerializedName

data class CollectionModel(
    @SerializedName("status_code") val status_code: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val poster_path: String?,
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("parts") val parts: Array<CollectionParts>?
)
