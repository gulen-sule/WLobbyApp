package com.example.wlobbyapp.data.service

import com.example.wlobbyapp.data.search.multiSearch.MultiSearchModel
import com.example.wlobbyapp.data.search.searchKeyword.SearchKeywordModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    @GET("sayfa")
//    fun getMovies(): Call<Model>


    @GET("3/search/keyword")
    fun searchKeyword(
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("query") query: String = " ",
        @Query("page") page: Int? = 1
    ): Call<SearchKeywordModel>

    @GET("3/search/multi")
    fun multiSearch(
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("query") query: String = "test",
        @Query("page") page: Int? = 1,
        @Query("include_adult") include_adult: Boolean? = true,
        @Query("region") region: String? = null,
        @Query("language") language: String? = null
    ): Call<MultiSearchModel>
}