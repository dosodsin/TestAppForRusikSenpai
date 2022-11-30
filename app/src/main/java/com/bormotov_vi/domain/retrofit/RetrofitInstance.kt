package com.bormotov_vi.domain.retrofit

import com.bormotov_vi.domain.user_interactor.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://jsonplaceholder.typicode.com/"

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

}