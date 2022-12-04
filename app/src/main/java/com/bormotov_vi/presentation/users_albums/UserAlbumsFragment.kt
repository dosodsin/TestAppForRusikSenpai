package com.bormotov_vi.presentation.users_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserAlbumsBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_albums.recycler.AlbumsAdapter
import com.bormotov_vi.presentation.users_photos.UserPhotosFragment
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment


class UserAlbumsFragment : BaseFragment() {

    private var binding: FragmentUserAlbumsBinding? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private var adapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAlbumsBinding.inflate(layoutInflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        val bundle = this.arguments
        interactor.receiveAlbums(bundle?.getInt("userId")) {
            val userPhotosFragment = UserPhotosFragment.newInstance()
            adapter = AlbumsAdapter(it) {
                bundle?.putInt("albumId", it.id)
                userPhotosFragment.arguments = bundle
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityMain, userPhotosFragment)
                    .addToBackStack("userPhotosFragment")
                    .commit()
            }
            binding?.recyclerViewPost?.adapter = adapter

        }

        imageBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserAlbumsFragment()
    }
}