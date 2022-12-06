package com.bormotov_vi.presentation.users_posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserPostsBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_comments.UserCommentsFragment
import com.bormotov_vi.presentation.users_posts.recycler.PostAdapter

class UserPostsFragment : BaseFragment() {

    private var binding: FragmentUserPostsBinding? = null
    private var adapter: PostAdapter? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPostsBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        interactor.receivePosts(this.arguments?.getInt(USER_ID)) { postItems ->
            adapter = PostAdapter(postItems) { postItem ->
                this.arguments?.putInt(POST_ID, postItem.id)
                val userCommentsFragment = UserCommentsFragment()
                userCommentsFragment.arguments = this.arguments
                navigate(POST_FRAGMENT_TAG, userCommentsFragment)
            }
            binding?.recyclerViewPost?.adapter = adapter

        }

        imageBack?.setOnClickListener {
            moveToPrevFragmentByToolbarBackImage()
        }

        return binding?.root
    }

    companion object {
        const val USER_ID = "userId"
        const val POST_ID = "postId"
        const val POST_FRAGMENT_TAG = "userCommentsFragment"
    }
}