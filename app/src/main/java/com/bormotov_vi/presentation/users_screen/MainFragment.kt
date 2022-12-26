package com.bormotov_vi.presentation.users_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentMainBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_screen.recycler.UserAdapter
import com.bormotov_vi.presentation.users_screen.viewModel.ScreenState
import com.bormotov_vi.presentation.users_screen.viewModel.UserItemsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment() {

    private val binding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: UserAdapter? = null
    private val viewModel: UserItemsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launchWhenCreated {
            viewModel.users.collect {
                when (it) {
                    is ScreenState.Loading -> {
                        binding.shimmer.startShimmer()
                    }

                    is ScreenState.Success -> {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.visibility = View.GONE
                        binding.mainFragmentRecyclerView.visibility = View.VISIBLE
                        adapter = UserAdapter(it.users) { userItem ->
                            val directionToUserPostsAndAlbumsFragment = MainFragmentDirections
                                .actionMainFragment2ToUserPostAndAlbumsFragment()
                                .setUserId(userItem.id)
                            findNavController()
                                .navigate(directionToUserPostsAndAlbumsFragment)
                        }
                        binding.mainFragmentRecyclerView.adapter = adapter
                    }

                }
            }
        }
        return binding.root
    }

}