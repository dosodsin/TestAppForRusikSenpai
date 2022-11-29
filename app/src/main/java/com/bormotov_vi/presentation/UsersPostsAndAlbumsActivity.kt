package com.bormotov_vi.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUsersPostsAndAlbumsBinding

class UsersPostsAndAlbumsActivity : AppCompatActivity() {

    private var binding: ActivityUsersPostsAndAlbumsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersPostsAndAlbumsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val arguments = intent.extras
        val userId = arguments?.getInt("userId")
        val postsButton = binding?.postButton
        val albumsButton = binding?.albumButton

        postsButton?.setOnClickListener {
            val intent = Intent(this@UsersPostsAndAlbumsActivity, UserPostsActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
        albumsButton?.setOnClickListener {
            val intent = Intent(this@UsersPostsAndAlbumsActivity, UserAlbumsActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
        binding?.toolbar?.albumsToolbarImageView?.setOnClickListener {
            super.onBackPressed()
        }
    }
}