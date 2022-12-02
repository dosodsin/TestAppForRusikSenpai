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
import com.bormotov_vi.presentation.users_comments.UserCommentsFragment
import com.bormotov_vi.presentation.users_posts.recycler.PostAdapter
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment

class UserPostsFragment : Fragment() {

    private var binding: FragmentUserPostsBinding? = null
    private var adapter: PostAdapter? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private val userCommentsFragment = UserCommentsFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPostsBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        interactor.receivePosts {
            adapter = PostAdapter(it) {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityMain, userCommentsFragment)
                    .commit()
            }
            binding?.recyclerViewPost?.adapter = adapter

        }

        imageBack?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, UserPostAndAlbumsFragment.newInstance())
                .addToBackStack("usersPostsAndAlbumsFragment")
                .commit()
        }

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserPostsFragment()
    }
}