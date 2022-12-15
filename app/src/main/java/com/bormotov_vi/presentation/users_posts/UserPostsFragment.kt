package com.bormotov_vi.presentation.users_posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.R
import com.bormotov_vi.databinding.FragmentUserPostsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_posts.recycler.PostAdapter
import com.bormotov_vi.presentation.users_posts.viewModel.PostItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPostsFragment : BaseFragment() {

    private val binding: FragmentUserPostsBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: PostAdapter? = null
    val viewModel: PostItemViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.arguments?.getInt(USER_ID)?.let { viewModel.receivePosts(it) }
        viewModel.posts.observe(viewLifecycleOwner) { postItems ->
            adapter = PostAdapter(postItems) { postItem ->

                navigate(POST_FRAGMENT_TAG, POST_ID, postItem.id)
            }
            binding.recyclerViewPost.adapter = adapter
        }

        with(binding) {
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }

    companion object {
        const val USER_ID = "userId"
        const val POST_ID = "postId"
        const val POST_FRAGMENT_TAG = R.id.action_userPostsFragment_to_userCommentsFragment
    }
}