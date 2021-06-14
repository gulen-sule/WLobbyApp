package com.example.wlobbyapp.data

import com.example.wlobbyapp.data.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var retrofit: ApiService? = null
    private val BASE_URL = "https://api.themoviedb.org/"

    init {
        initClient()
    }

    fun getClient() = retrofit // bununla eger initClientta degisiklik yapilmayacaksa
    //sana surekli aynı nesnenin  degerini donuyor
    //bazen degisiklik yapma isteginden dolayı mutable live data kullanılabilir o da observer mimarisine dayaniyor

    private fun initClient(): ApiService? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()//bunun ne ise yaradigina yeniden bak

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client).build()
            .create(ApiService::class.java)

        return retrofit
    }

    companion object {//aynı objeyi yeniden yeniden olusturmamak icin kullaniliyor

        private var apiClient: ApiClient? = null

        fun getInstance(): ApiClient? {

            if (apiClient == null) {
                apiClient = ApiClient()
                return apiClient
            }

            return apiClient as ApiClient
        }
    }
}