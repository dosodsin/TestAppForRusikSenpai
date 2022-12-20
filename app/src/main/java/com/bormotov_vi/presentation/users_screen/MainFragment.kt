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
import com.bormotov_vi.presentation.users_screen.viewModel.UserItemsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment() {

    private val binding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: UserAdapter? = null
    private val viewModel: UserItemsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launch {
            viewModel.users.collect { userItems ->
                adapter = UserAdapter(userItems) { userItem ->
                    val directionToUserPostsAndAlbumsFragment = MainFragmentDirections
                        .actionMainFragment2ToUserPostAndAlbumsFragment()
                        .setUserId(userItem.id)
                    findNavController()
                        .navigate(directionToUserPostsAndAlbumsFragment)
                }
                binding.mainFragmentRecyclerView.adapter = adapter
            }
            binding.mainFragmentProgressBar.visibility = View.GONE
        }
        return binding.root
    }

}