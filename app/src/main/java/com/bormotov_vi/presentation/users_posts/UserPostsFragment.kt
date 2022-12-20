package com.bormotov_vi.presentation.users_posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentUserPostsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_posts.recycler.PostAdapter
import com.bormotov_vi.presentation.users_posts.viewModel.PostItemViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPostsFragment : BaseFragment() {

    private val binding: FragmentUserPostsBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: PostAdapter? = null
    private val viewModel: PostItemViewModel by viewModel()
    private val args: UserPostsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.receivePosts(args.userId)

        lifecycleScope.launch {
            viewModel.posts.collect { postItems ->
                adapter = PostAdapter(postItems) { postItem ->
                    val directionToUserComments = UserPostsFragmentDirections
                        .actionUserPostsFragmentToUserCommentsFragment()
                        .setPostId(args.postId)
                    findNavController()
                        .navigate(directionToUserComments)
                }
                binding.recyclerViewPost.adapter = adapter
            }
        }


        with(binding) {
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }
}