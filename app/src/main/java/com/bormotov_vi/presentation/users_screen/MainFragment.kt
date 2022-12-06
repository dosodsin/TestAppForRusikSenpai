package com.bormotov_vi.presentation.users_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bormotov_vi.databinding.FragmentMainBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment
import com.bormotov_vi.presentation.users_screen.recycler.UserAdapter
import com.bormotov_vi.presentation.users_screen.viewModel.UserItemsViewModel


class MainFragment : BaseFragment() {

    private var binding: FragmentMainBinding? = null
    private var adapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val bundle = Bundle()
        val viewModel = ViewModelProvider(this).get(UserItemsViewModel::class.java)
        viewModel.init()
        viewModel.users.observe(viewLifecycleOwner) { userItems ->
            adapter = UserAdapter(userItems) { userItem ->
                bundle.putInt(USER_ID, userItem.id)
                val usersPostsAndAlbumsFragment = UserPostAndAlbumsFragment()
                usersPostsAndAlbumsFragment.arguments = bundle
                navigate(POSTS_ALBUMS_FRAGMENT_TAG, usersPostsAndAlbumsFragment)
            }
            binding?.mainFragmentRecyclerView?.adapter = adapter
        }
        return binding?.root
    }

    companion object {
        const val USER_ID = "userId"
        const val POSTS_ALBUMS_FRAGMENT_TAG = "usersPostsAndAlbumsFragment"
    }
}