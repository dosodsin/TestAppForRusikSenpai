package com.bormotov_vi.presentation.users_posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserPostsBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_comments.UserCommentsFragment
import com.bormotov_vi.presentation.users_posts.recycler.PostAdapter
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment

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
        val bundle = this.arguments
        interactor.receivePosts(bundle?.getInt("userId")) {
            adapter = PostAdapter(it) {
                bundle?.putInt("postId", it.id)
                val userCommentsFragment = UserCommentsFragment.newInstance()
                userCommentsFragment.arguments = bundle
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityMain, userCommentsFragment)
                    .addToBackStack("userCommentsFragment")
                    .commit()
            }
            binding?.recyclerViewPost?.adapter = adapter

        }

        imageBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserPostsFragment()
    }
}