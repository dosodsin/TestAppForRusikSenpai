package com.bormotov_vi.presentation.users_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserPhotosBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.users_albums.UserAlbumsFragment
import com.bormotov_vi.presentation.users_photos.recycler.PhotoAdapter
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment

class UserPhotosFragment : Fragment() {

    private var binding: FragmentUserPhotosBinding? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private var adapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPhotosBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        interactor.receivePhotos {
            adapter = PhotoAdapter(it)
            binding!!.photoRecyclerView.adapter = adapter
        }

        imageBack?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, UserAlbumsFragment.newInstance())
                .addToBackStack("userAlbumsFragment")
                .commit()
        }

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserPhotosFragment()
    }
}