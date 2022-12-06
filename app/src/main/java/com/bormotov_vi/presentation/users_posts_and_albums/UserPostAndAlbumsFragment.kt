package com.bormotov_vi.presentation.users_posts_and_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bormotov_vi.databinding.FragmentUserPostAndAlbumsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_albums.UserAlbumsFragment
import com.bormotov_vi.presentation.users_posts.UserPostsFragment


class UserPostAndAlbumsFragment : BaseFragment() {

    private var binding: FragmentUserPostAndAlbumsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPostAndAlbumsBinding.inflate(inflater, container, false)
        val bundle = this.arguments
        val postButton = binding?.postButton
        val albumButton = binding?.albumButton
        val backImage = binding?.toolbar?.backImage

        postButton?.setOnClickListener {
            val userPostFragment = UserPostsFragment()
            userPostFragment.arguments = bundle
            navigate(POST_FRAGMENT_TAG, userPostFragment)
        }
        albumButton?.setOnClickListener {
            val userAlbumFragment = UserAlbumsFragment()
            userAlbumFragment.arguments = bundle
            navigate(ALBUM_FRAGMENT_TAG, userAlbumFragment)
        }
        backImage?.setOnClickListener {
            moveToPrevFragmentByToolbarBackImage()
        }

        return binding?.root
    }


    companion object {

        const val POST_FRAGMENT_TAG = "userPostFragment"
        const val ALBUM_FRAGMENT_TAG = "userAlbumFragment"

    }
}