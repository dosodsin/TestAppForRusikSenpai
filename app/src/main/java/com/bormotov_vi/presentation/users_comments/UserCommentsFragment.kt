package com.bormotov_vi.presentation.users_comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentUserCommentsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_comments.recycler.CommentAdapter
import com.bormotov_vi.presentation.users_comments.viewModel.UserCommentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserCommentsFragment : BaseFragment() {

    private val binding: FragmentUserCommentsBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: CommentAdapter? = null
    val viewModel: UserCommentsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.arguments?.getInt(POST_ID)?.let { viewModel.receiveComments(it) }
        viewModel.comments.observe(viewLifecycleOwner) { commentItems ->
            adapter = CommentAdapter(commentItems)
            binding.commentsRecyclerView.adapter = adapter
        }

        with(binding) {
            toolbar.backImage.setOnClickListener {
                moveToPrevFragmentByToolbarBackImage()
            }
        }

        return binding.root
    }

    companion object {
        const val POST_ID = "postId"
    }
}