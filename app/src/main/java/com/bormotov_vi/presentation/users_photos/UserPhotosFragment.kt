package com.bormotov_vi.presentation.users_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bormotov_vi.databinding.FragmentUserPhotosBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_photos.recycler.PhotoAdapter
import com.bormotov_vi.presentation.users_photos.viewModel.UserPhotosViewModel

class UserPhotosFragment : BaseFragment() {

    private var binding: FragmentUserPhotosBinding? = null
    private var adapter: PhotoAdapter? = null
    val viewModel: UserPhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPhotosBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        this.arguments?.getInt(ALBUM_ID)?.let { viewModel.init(it) }
        this.arguments?.getInt(ALBUM_ID)?.let { viewModel.init(it) }
        viewModel.photos.observe(viewLifecycleOwner) { photoItems ->
            adapter = PhotoAdapter(photoItems)
            binding?.photoRecyclerView?.adapter = adapter
        }

        imageBack?.setOnClickListener {
            moveToPrevFragmentByToolbarBackImage()
        }

        return binding?.root
    }

    companion object {
        const val ALBUM_ID = "albumId"
    }
}