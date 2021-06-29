package com.example.wlobbyapp.data.api.models.detailedModels.MovieDetailed

import com.google.gson.annotations.SerializedName


data class Genres (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
)