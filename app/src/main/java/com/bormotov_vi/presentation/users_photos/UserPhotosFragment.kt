package com.bormotov_vi.presentation.users_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserPhotosBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.users_photos.recycler.PhotoAdapter

class UserPhotosFragment : Fragment() {

    private var binding: FragmentUserPhotosBinding? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private var adapter: PhotoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPhotosBinding.inflate(layoutInflater, container, false)

        interactor.receivePhotos {
            adapter = PhotoAdapter(it)
            binding!!.photoRecyclerView.adapter = adapter
        }

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserPhotosFragment()
    }
}