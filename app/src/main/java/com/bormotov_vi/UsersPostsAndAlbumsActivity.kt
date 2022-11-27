package com.bormotov_vi

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

        var arguments = intent.extras
        var userId = arguments?.getInt("userId")
        var postsButton = binding?.postButton
        var albumsButton = binding?.albumButton
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
        binding!!.activityPostAndAlbumsBackImage.setOnClickListener {
            super.onBackPressed()
        }
    }
}