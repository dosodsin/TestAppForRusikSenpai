package com.bormotov_vi.presentation.users_posts_and_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
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
        val postsButton = binding?.postButton
        val albumsButton = binding?.albumButton
        val imageBack = binding?.toolbar?.backImage
        var bundle = this.arguments

        postsButton?.setOnClickListener {
            val userPostFragment = UserPostsFragment.newInstance()
            userPostFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, userPostFragment)
                .addToBackStack("userPostFragment")
                .commit()
        }
        albumsButton?.setOnClickListener {
            val userAlbumFragment = UserAlbumsFragment.newInstance()
            userAlbumFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, userAlbumFragment)
                .addToBackStack("userAlbumFragment")
                .commit()
        }
        imageBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        return binding?.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = UserPostAndAlbumsFragment()
    }
}