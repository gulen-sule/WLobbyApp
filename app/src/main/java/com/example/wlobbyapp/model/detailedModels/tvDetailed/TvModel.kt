package com.example.wlobbyapp.model.detailedModels.tvDetailed

import com.google.gson.annotations.SerializedName

data class TvModel(
    @SerializedName("status_code") val status_code: Int?=null,
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @SerializedName("created_by") val created_by: Array<Created_by>?,
    @SerializedName("episode_run_time") val episode_run_time: Array<Int>?,
    @SerializedName("first_air_date") val first_air_date: String?,
    @SerializedName("genres") val genres: Array<Genres>?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("in_production") val in_production: Boolean?,
    @SerializedName("languages") val languages: Array<String>?,
    @SerializedName("last_air_date") val last_air_date: String?,
    @SerializedName("last_episode_to_air") val last_episode_to_air: Last_episode_to_air?,
    @SerializedName("name") val name: String?,
    @SerializedName("next_episode_to_air") val next_episode_to_air: String?=null,
    @SerializedName("networks") val networks: Array<Networks>?,
    @SerializedName("number_of_episodes") val number_of_episodes: Int?,
    @SerializedName("number_of_seasons") val number_of_seasons: Int?,
    @SerializedName("origin_country") val origin_country: Array<String>?,
    @SerializedName("original_language") val original_language: String?,
    @SerializedName("original_name") val original_name: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Number?,
    @SerializedName("poster_path") val poster_path: String?,
    @SerializedName("production_companies") val production_companies: Array<Production_companies>?,
    @SerializedName("production_countries") val production_countries: Array<Production_countries>?,
    @SerializedName("seasons") val seasons:Array<Seasons>?,
    @SerializedName("spoken_languages") val spoken_languages: Array<Spoken_languages>?,
    @SerializedName("status") val status: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("vote_average") val vote_average: Number?,
    @SerializedName("vote_count") val vote_count: Int?=null
)