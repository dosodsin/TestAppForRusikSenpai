package com.bormotov_vi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityMainBinding
import com.bormotov_vi.model.Users
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private var users = ArrayList<Users>()
    private val URL = "https://jsonplaceholder.typicode.com/users"
    private var client: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {

                    } else {
                        Log.d("myTag1", response.body!!.string())
                        val body: String? =response.body?.string()
                        if (body != null) {
                            Log.d("myTag2", body)
                        }
                        val  gson=GsonBuilder().create()
                        val result=gson.fromJson(body, Array<Users>::class.java)
                        Log.d("userList", result.toString())
                        adapter = UserAdapter(users)
                        binding.recyclerView.adapter = adapter
                    }
                }
            }
        })

    }


}