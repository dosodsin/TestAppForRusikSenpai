package com.bormotov_vi.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUserPostsBinding
import com.bormotov_vi.domain.model.post.UserPostItem
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

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
        val arguments = intent.extras
        userId = arguments?.getInt("userId")

        val postClickListener: PostAdapter.PostActionListener =
            object : PostAdapter.PostActionListener {
                override fun onPostClickListener(post: UserPostItem, position: Int) {
                    val intent = Intent(this@UserPostsActivity, CommentsActivity::class.java)
                    intent.putExtra("postId", post.id)
                    startActivity(intent)
                }
            }

        getPosts {
            runOnUiThread {
                adapter = PostAdapter(it, postClickListener)
                binding!!.recyclerViewPost.adapter = adapter
            }
        }

        binding?.toolbar?.albumsToolbarImageView?.setOnClickListener {
            onBackPressed()
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

    private fun parseResult(result: List<UserPostItem>): List<UserPostItem> =
        result.filter { it.userId == userId }
}