package com.bormotov_vi.presentation.users_comments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityCommentsBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.domain.user_interactor.UsersInteractorImpl
import com.bormotov_vi.presentation.users_comments.recycler.CommentAdapter

class CommentsActivity : AppCompatActivity() {

    private var binding: ActivityCommentsBinding? = null
    private var adapter: CommentAdapter? = null
    private var postId: Int? = null
    private val interactor: UsersInteractor = UsersInteractorImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val arguments = intent.extras
        postId = arguments?.getInt("postId")

        interactor.receiveComments {
            runOnUiThread {
                adapter = CommentAdapter(it)
                binding?.commentsRecyclerView?.adapter = adapter
            }
        }

        binding?.toolbar?.backImage?.setOnClickListener {
            super.onBackPressed()
        }
    }
}