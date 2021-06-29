package com.example.wlobbyapp.data.api.models.detailedModels.MovieDetailed

import com.google.gson.annotations.SerializedName

data class Production_companies (

	@SerializedName("id") val id : Int,
	@SerializedName("logo_path") val logo_path : String,
	@SerializedName("name") val name : String,
	@SerializedName("origin_country") val origin_country : String
)