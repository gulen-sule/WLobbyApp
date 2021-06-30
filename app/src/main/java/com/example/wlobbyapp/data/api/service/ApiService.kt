package com.example.wlobbyapp.data.api.service

import com.example.wlobbyapp.data.api.models.detailedModels.CollectionDetailed.CollectionModel
import com.example.wlobbyapp.data.api.models.detailedModels.MovieDetailed.MovieModel
import com.example.wlobbyapp.data.api.models.detailedModels.peopleDetailed.PeopleModel
import com.example.wlobbyapp.data.api.models.detailedModels.tvDetailed.TvModel
import com.example.wlobbyapp.data.api.models.searchModels.multiSearch.MultiSearchModel
import com.example.wlobbyapp.data.api.models.searchModels.searchCollection.CollectionSearchModel
import com.example.wlobbyapp.data.api.models.searchModels.searchKeyword.SearchKeywordModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface ApiService {

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

    @GET("3/search/multi")
    suspend fun multiSearchPaging(
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("query") query: String = "test",
        @Query("page") page: Int? = 1,
        @Query("include_adult") include_adult: Boolean? = true,
        @Query("region") region: String? = null,
        @Query("language") language: String? = null
    ): Response<MultiSearchModel>

    @GET("3/search/collection")
    suspend fun collectionSearch(
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("query") query: String = "test",
        @Query("page") page: Int? = 1,
    ): Response<CollectionSearchModel>

    @GET("3/movie/{movie_id}")
    suspend fun movieDetailed(
        @Path("movie_id") movie_id: Int = 0,
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("language") language: String? = "tr",
        @Query("append_to_response") append_to_response: String? = null

    ): Response<MovieModel>

    @GET("3/collection/{collection_id}")
    suspend fun collectionDetailed(
        @Path("collection_id") collection_id: Int = 0,
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("language") language: String? = "tr",
        @Query("append_to_response") append_to_response: String? = null

    ): Response<CollectionModel>


    @GET("3/tv/{tv_id}")
    suspend fun tvDetailed(
        @Path("tv_id") tv_id: Int = 10,
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("language") language: String? = null,
        @Query("append_to_response") append_to_response: String? = null

    ): Response<TvModel>

    @GET("3/person/{person_id}")
    suspend fun peopleDetailed(
        @Path("person_id") person_id: Int = 0,
        @Query("api_key") api_key: String = "f9f08b7835b905ced52616110a97a3c8",
        @Query("language") language: String? = "tr",
        @Query("append_to_response") append_to_response: String? = null

    ): Response<PeopleModel>

}