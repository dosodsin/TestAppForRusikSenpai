package com.bormotov_vi.presentation.users_comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bormotov_vi.databinding.FragmentUserCommentsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_comments.recycler.CommentAdapter
import com.bormotov_vi.presentation.users_comments.viewModel.UserCommentsViewModel


class UserCommentsFragment : BaseFragment() {

    private var binding: FragmentUserCommentsBinding? = null
    private var adapter: CommentAdapter? = null
    val viewModel: UserCommentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserCommentsBinding.inflate(inflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        val bundle = this.arguments
        viewModel.postId = this.arguments?.getInt(POST_ID)
        viewModel.comments.observe(viewLifecycleOwner) { commentItems ->
            adapter = CommentAdapter(commentItems)
            binding?.commentsRecyclerView?.adapter = adapter
        }

        imageBack?.setOnClickListener {
            moveToPrevFragmentByToolbarBackImage()
        }

        return binding?.root
    }

    companion object {
        const val POST_ID = "postId"
    }
}