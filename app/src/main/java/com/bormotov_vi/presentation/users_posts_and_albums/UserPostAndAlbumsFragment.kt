package com.bormotov_vi.presentation.users_posts_and_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.R
import com.bormotov_vi.databinding.FragmentUserPostAndAlbumsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_screen.MainFragment


class UserPostAndAlbumsFragment : BaseFragment() {

    private val binding: FragmentUserPostAndAlbumsBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(binding) {
            postButton.setOnClickListener {
                navigate(
                    POST_FRAGMENT_TAG,
                    MainFragment.USER_ID,
                    requireArguments().getInt(MainFragment.USER_ID)
                )
            }
            albumButton.setOnClickListener {
                navigate(
                    ALBUM_FRAGMENT_TAG,
                    MainFragment.USER_ID,
                    requireArguments().getInt(MainFragment.USER_ID)
                )
            }
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }

    companion object {

        const val POST_FRAGMENT_TAG = R.id.action_userPostAndAlbumsFragment_to_userPostsFragment
        const val ALBUM_FRAGMENT_TAG = R.id.action_userPostAndAlbumsFragment_to_userAlbumsFragment
        const val POSTS_ALBUMS_TAG = R.id.action_mainFragment2_to_userPostAndAlbumsFragment

    }
}