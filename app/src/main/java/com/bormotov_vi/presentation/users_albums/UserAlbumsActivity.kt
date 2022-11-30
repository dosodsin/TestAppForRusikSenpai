package com.bormotov_vi.presentation.users_albums

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.ActivityUserAlbumsBinding
import com.bormotov_vi.domain.retrofit.Repository
import com.bormotov_vi.presentation.users_albums.recycler.AlbumsAdapter
import com.bormotov_vi.presentation.users_photos.UserPhotosActivity

class UserAlbumsActivity : AppCompatActivity() {

    private var adapter: AlbumsAdapter? = null
    private var userId: Int? = null
    private var binding: ActivityUserAlbumsBinding? = null
    private val repository: Repository = RusikSenpaiApplication.repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAlbumsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var arguments = intent.extras
        userId = arguments?.getInt("userId")

        repository.getAlbums {
            runOnUiThread {
                adapter = AlbumsAdapter(it) {
                    val intent = Intent(this@UserAlbumsActivity, UserPhotosActivity::class.java)
                    intent.putExtra("albumId", it.id)
                    startActivity(intent)
                }
                binding?.recyclerViewPost?.adapter = adapter
            }
        }

        binding?.toolbar?.backImage?.setOnClickListener {
            onBackPressed()
        }
    }
}