package com.bormotov_vi.presentation.users_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bormotov_vi.databinding.FragmentUserAlbumsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_albums.recycler.AlbumsAdapter
import com.bormotov_vi.presentation.users_albums.viewModel.UserAlbumViewModel
import com.bormotov_vi.presentation.users_photos.UserPhotosFragment


class UserAlbumsFragment : BaseFragment() {

    private var binding: FragmentUserAlbumsBinding? = null
    private var adapter: AlbumsAdapter? = null
    val viewModel: UserAlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAlbumsBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        viewModel.userId = this.arguments?.getInt(USER_ID)
        viewModel.albums.observe(viewLifecycleOwner) { albumsItems ->
            val userPhotosFragment = UserPhotosFragment()
            adapter = AlbumsAdapter(albumsItems) { albumItem ->
                this.arguments?.putInt(ALBUM_ID, albumItem.id)
                userPhotosFragment.arguments = this.arguments
                navigate(PHOTO_FRAGMENT_TAG, userPhotosFragment)
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
        const val ALBUM_ID = "albumId"
        const val PHOTO_FRAGMENT_TAG = "userPhotosFragment"
    }
}