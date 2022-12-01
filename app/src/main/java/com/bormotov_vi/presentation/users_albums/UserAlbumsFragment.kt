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
import com.bormotov_vi.presentation.users_albums.recycler.AlbumsAdapter
import com.bormotov_vi.presentation.users_photos.UserPhotosFragment


class UserAlbumsFragment : Fragment() {

    private var binding: FragmentUserAlbumsBinding? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private var adapter: AlbumsAdapter? = null
    private val userPhotosFragment = UserPhotosFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAlbumsBinding.inflate(layoutInflater, container, false)

        interactor.receiveAlbums {
            adapter = AlbumsAdapter(it) {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityMain, userPhotosFragment)
                    .commit()
            }
            binding?.recyclerViewPost?.adapter = adapter

        }

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserAlbumsFragment()
    }
}