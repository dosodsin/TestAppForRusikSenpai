package com.bormotov_vi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityCommentsBinding
import com.bormotov_vi.model.comment.Comment
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class CommentsActivity : AppCompatActivity() {

    private var binding: ActivityCommentsBinding? = null
    private var adapter: CommentAdapter? = null
    private val URL = "https://jsonplaceholder.typicode.com/comments"
    private var client: OkHttpClient = OkHttpClient()
    private var postId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val arguments = intent.extras
        postId = arguments?.getInt("postId")

        getComments {
            runOnUiThread {
                adapter = CommentAdapter(it)
                binding!!.commentsRecyclerView.adapter = adapter
            }
        }

        binding!!.activityCommentsBackImage.setOnClickListener {
            super.onBackPressed()
        }

    }

    private fun getComments(callback: (List<Comment>) -> Unit) {
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
                    var result = gson.fromJson(body, Array<Comment>::class.java).toList()
                    result = parseResult(result)
                    callback(result)
                }

            }
        })
    }


    private fun parseResult(result: List<Comment>): List<Comment> {
        return result.filter {
            it.postId == postId
        }
    }

}