package com.example.wlobbyapp.data.api.models.detailedModels.CollectionDetailed

import com.google.gson.annotations.SerializedName

data class CollectionParts(
    @SerializedName("poster_path") val poster_path: String? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_date") val release_date: String? = null,
    @SerializedName("original_title") val original_title: String? = null,
    @SerializedName("genre_ids") val genre_ids: Array<Int>? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("original_language") val original_language: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("backdrop_path") val backdrop_path: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("vote_count") val vote_count: Int? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("vote_average") val vote_average: Double? = null,
)
