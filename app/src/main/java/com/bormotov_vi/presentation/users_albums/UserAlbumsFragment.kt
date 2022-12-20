package com.bormotov_vi.presentation.users_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentUserAlbumsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_albums.recycler.AlbumsAdapter
import com.bormotov_vi.presentation.users_albums.viewModel.UserAlbumViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserAlbumsFragment : BaseFragment() {

    private val binding: FragmentUserAlbumsBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: AlbumsAdapter? = null
    private val viewModel: UserAlbumViewModel by viewModel()
    private val args: UserAlbumsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.receiveAlbums(args.userId)
        lifecycleScope.launch {
            viewModel.albums.collect { albumsItems ->
                adapter = AlbumsAdapter(albumsItems) { albumItem ->
                    val directionsToUserPhotos = UserAlbumsFragmentDirections
                        .actionUserAlbumsFragmentToUserPhotosFragment()
                        .setAlbumId(albumItem.id)
                    findNavController()
                        .navigate(directionsToUserPhotos)
                }
                binding.recyclerViewPost.adapter = adapter
            }
        }

        with(binding) {
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }
}