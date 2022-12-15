package com.bormotov_vi.presentation.users_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.R
import com.bormotov_vi.databinding.FragmentUserAlbumsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_albums.recycler.AlbumsAdapter
import com.bormotov_vi.presentation.users_albums.viewModel.UserAlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserAlbumsFragment : BaseFragment() {

    private val binding: FragmentUserAlbumsBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: AlbumsAdapter? = null
    val viewModel: UserAlbumViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.arguments?.getInt(USER_ID)?.let { viewModel.receiveAlbums(it) }
        viewModel.albums.observe(viewLifecycleOwner) { albumsItems ->
            adapter = AlbumsAdapter(albumsItems) { albumItem ->
                navigate(PHOTO_FRAGMENT_TAG, ALBUM_ID, albumItem.id)
            }
            binding.recyclerViewPost.adapter = adapter
        }

        with(binding) {
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }

    companion object {
        const val USER_ID = "userId"
        const val ALBUM_ID = "albumId"
        const val PHOTO_FRAGMENT_TAG = R.id.action_userAlbumsFragment_to_userPhotosFragment
    }
}