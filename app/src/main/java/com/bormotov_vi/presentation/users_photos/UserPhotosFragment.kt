package com.bormotov_vi.presentation.users_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserPhotosBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_photos.recycler.PhotoAdapter

class UserPhotosFragment : BaseFragment() {

    private var binding: FragmentUserPhotosBinding? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private var adapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPhotosBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        interactor.receivePhotos(this.arguments?.getInt(ALBUM_ID)) { photoItems ->
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