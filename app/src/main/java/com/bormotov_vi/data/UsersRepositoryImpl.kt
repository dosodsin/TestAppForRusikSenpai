package com.bormotov_vi.data

import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersRepository
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val URL = "https://jsonplaceholder.typicode.com/users"

class UsersRepositoryImpl : UsersRepository {
    private var client: OkHttpClient = OkHttpClient()

    override fun receiveUsers(callback: (List<UsersItem>) -> Unit) {
        val request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<UsersItem>::class.java).toList()
                    callback(result)
                }
            }
        })
    }
}