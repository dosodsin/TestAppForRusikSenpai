package com.bormotov_vi.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUserAlbumsBinding
import com.bormotov_vi.domain.model.album.Album
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class UserAlbumsActivity : AppCompatActivity() {

    private var adapter: AlbumsAdapter? = null
    private val URL = "https://jsonplaceholder.typicode.com/albums"
    private var client: OkHttpClient = OkHttpClient()
    private var userId: Int? = null
    private var binding: ActivityUserAlbumsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAlbumsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var arguments = intent.extras
        userId = arguments?.getInt("userId")

        getAlbums {
            runOnUiThread {
                adapter = AlbumsAdapter(it) {
                    val intent = Intent(this@UserAlbumsActivity, UserPhotosActivity::class.java)
                    intent.putExtra("albumId", it.id)
                    startActivity(intent)
                }
                binding!!.recyclerViewPost.adapter = adapter
            }
        }

        binding?.toolbar?.albumsToolbarImageView?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getAlbums(callback: (List<Album>) -> Unit) {
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
                    val result = gson.fromJson(body, Array<Album>::class.java).toList()
                    val filteredResult = result.filter { it.userId == userId }
                    callback(filteredResult)
                }
            }
        })
    }
}