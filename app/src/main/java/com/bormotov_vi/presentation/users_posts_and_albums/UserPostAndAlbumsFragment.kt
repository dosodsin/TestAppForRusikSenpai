package com.bormotov_vi.presentation.users_posts_and_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.R
import com.bormotov_vi.databinding.FragmentUserPostAndAlbumsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment


class UserPostAndAlbumsFragment : BaseFragment() {

    private val binding: FragmentUserPostAndAlbumsBinding by viewBinding(CreateMethod.INFLATE)
    private val args: UserPostAndAlbumsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(binding) {
            postButton.setOnClickListener {
                val directionToUserPosts = UserPostAndAlbumsFragmentDirections
                    .actionUserPostAndAlbumsFragmentToUserPostsFragment()
                    .setUserId(args.userId)
                findNavController()
                    .navigate(directionToUserPosts)
            }
            albumButton.setOnClickListener {
                val directionToUserAlbums = UserPostAndAlbumsFragmentDirections
                    .actionUserPostAndAlbumsFragmentToUserAlbumsFragment()
                    .setUserId(args.userId)
                findNavController()
                    .navigate(directionToUserAlbums)
            }
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }
}