package com.bormotov_vi.presentation.users_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentUserPhotosBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_photos.recycler.PhotoAdapter
import com.bormotov_vi.presentation.users_photos.viewModel.UserPhotosViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPhotosFragment : BaseFragment() {

    private val binding: FragmentUserPhotosBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: PhotoAdapter? = null
    private val viewModel: UserPhotosViewModel by viewModel()
    private val args: UserPhotosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.receivePhotos(args.albumId)

        lifecycleScope.launch {
            viewModel.photos.collect { photoItems ->
                adapter = PhotoAdapter(photoItems)
                binding.photoRecyclerView.adapter = adapter
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