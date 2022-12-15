package com.bormotov_vi.presentation.users_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentMainBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment
import com.bormotov_vi.presentation.users_screen.recycler.UserAdapter
import com.bormotov_vi.presentation.users_screen.viewModel.UserItemsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment() {

    private val binding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: UserAdapter? = null
    val viewModel: UserItemsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.users.observe(viewLifecycleOwner) { userItems ->
            adapter = UserAdapter(userItems) { userItem ->
                navigate(UserPostAndAlbumsFragment.POSTS_ALBUMS_TAG, USER_ID, userItem.id)
            }
            binding.mainFragmentRecyclerView.adapter = adapter
        }
        return binding.root
    }

    companion object {
        const val USER_ID = "userId"
    }
}