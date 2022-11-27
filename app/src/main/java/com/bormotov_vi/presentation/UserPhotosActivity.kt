package com.bormotov_vi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUserPhotosBinding
import com.bormotov_vi.domain.model.photo.Photo
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class UserPhotosActivity : AppCompatActivity() {

    private var binding: ActivityUserPhotosBinding? = null
    private var adapter: PhotoAdapter? = null
    private val URL = "https://jsonplaceholder.typicode.com/photos"
    private var client: OkHttpClient = OkHttpClient()
    private var albumId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPhotosBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val arguments = intent.extras
        albumId = arguments?.getInt("albumId")

        getPhotos {
            runOnUiThread {
                adapter = PhotoAdapter(it)
                binding!!.photoRecyclerView.adapter = adapter
            }
        }

    }

    private fun getPhotos(callback: (List<Photo>) -> Unit) {
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
                    var result = gson.fromJson(body, Array<Photo>::class.java).toList()
                    result = parseResult(result)
                    callback(result)
                }

            }
        })
    }

    private fun parseResult(result: List<Photo>): List<Photo> =
        result.filter { it.albumId == albumId }

}