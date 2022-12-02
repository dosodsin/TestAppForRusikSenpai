package com.bormotov_vi.presentation.users_posts_and_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
import com.bormotov_vi.databinding.FragmentUserPostAndAlbumsBinding
import com.bormotov_vi.presentation.users_albums.UserAlbumsFragment
import com.bormotov_vi.presentation.users_posts.UserPostsFragment
import com.bormotov_vi.presentation.users_screen.MainFragment


class UserPostAndAlbumsFragment : Fragment() {

    private var binding: FragmentUserPostAndAlbumsBinding? = null
    private val userPostFragment = UserPostsFragment.newInstance()
    private val userAlbumFragment = UserAlbumsFragment.newInstance()
    private var bundle = this.arguments

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPostAndAlbumsBinding.inflate(inflater, container, false)
        val postsButton = binding?.postButton
        val albumsButton = binding?.albumButton
        val imageBack = binding?.toolbar?.backImage
        val test=bundle?.getInt("userId")

        postsButton?.setOnClickListener {
            userPostFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, userPostFragment)
                .commit()
        }
        albumsButton?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, userAlbumFragment)
                .commit()
        }
        imageBack?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, MainFragment.newInstance())
                .addToBackStack("mainFragment")
                .commit()
        }


        return binding?.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = UserPostAndAlbumsFragment()
    }
}