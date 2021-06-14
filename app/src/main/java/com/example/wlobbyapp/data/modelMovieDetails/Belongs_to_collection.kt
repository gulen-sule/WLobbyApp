package com.example.wlobbyapp.data.modelMovieDetails

import com.google.gson.annotations.SerializedName


data class Belongs_to_collection (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("poster_path") val poster_path : String,
	@SerializedName("backdrop_path") val backdrop_path : String
)