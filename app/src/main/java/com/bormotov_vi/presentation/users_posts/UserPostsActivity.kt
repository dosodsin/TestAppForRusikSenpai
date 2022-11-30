package com.bormotov_vi.presentation.users_posts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUserPostsBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.domain.user_interactor.UsersInteractorImpl
import com.bormotov_vi.presentation.users_comments.CommentsActivity
import com.bormotov_vi.presentation.users_posts.recycler.PostAdapter

class UserPostsActivity : AppCompatActivity() {

    private var userId: Int? = null
    private var binding: ActivityUserPostsBinding? = null
    private var adapter: PostAdapter? = null
    private val interactor: UsersInteractor = UsersInteractorImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPostsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val arguments = intent.extras
        userId = arguments?.getInt("userId")

        interactor.receivePosts {
            runOnUiThread {
                adapter = PostAdapter(it) {
                    val intent = Intent(this@UserPostsActivity, CommentsActivity::class.java)
                    intent.putExtra("postId", it.id)
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