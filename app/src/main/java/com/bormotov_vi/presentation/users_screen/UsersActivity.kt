package com.bormotov_vi.presentation.users_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.ActivityMainBinding
import com.bormotov_vi.domain.retrofit.Repository
import com.bormotov_vi.presentation.users_posts_and_albums.UsersPostsAndAlbumsActivity
import com.bormotov_vi.presentation.users_screen.recycler.UserAdapter

class UsersActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var adapter: UserAdapter? = null
    private val repository: Repository = RusikSenpaiApplication.repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        repository.getUsers { response ->
            if (response.isSuccessful) {
                runOnUiThread {
                    adapter = UserAdapter(response.body()!!) {
                        val intent = Intent(this@UsersActivity, UsersPostsAndAlbumsActivity::class.java)
                        intent.putExtra("userId", it.id)
                        startActivity(intent)
                    }
                    binding?.recyclerView?.adapter = adapter
                }
            }
        }
    }
}