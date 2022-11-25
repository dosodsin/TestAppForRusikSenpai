package com.bormotov_vi

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUserPostsBinding
import com.bormotov_vi.model.post.UserPostItem
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import kotlin.streams.toList

class UserPostsActivity : AppCompatActivity() {

    private var userId: Int? = null
    private var binding: ActivityUserPostsBinding? = null
    private var adapter: PostAdapter? = null
    private val URL = "https://jsonplaceholder.typicode.com/posts"
    private var client: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPostsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        var arguments = intent.extras
        userId = arguments?.getInt("userId")

        getPosts {
            runOnUiThread {
                adapter = PostAdapter(it)
                binding!!.recyclerViewPost.adapter = adapter
            }
        }
    }


    private fun getPosts(callback: (List<UserPostItem>) -> Unit) {
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
                    var result = gson.fromJson(body, Array<UserPostItem>::class.java).toList()
                    result = parseResult(result)
                    callback(result)
                }

            }
        })
    }


    private fun parseResult(result: List<UserPostItem>): List<UserPostItem> {
        var filtredPostList = ArrayList<UserPostItem>()
        for (i in result.indices) {
            if (result[i].userId == userId) {
                filtredPostList.add(result[i])
            }
        }
        return filtredPostList
    }
}