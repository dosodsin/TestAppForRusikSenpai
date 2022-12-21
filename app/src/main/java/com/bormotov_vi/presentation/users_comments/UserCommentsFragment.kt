package com.bormotov_vi.presentation.users_comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bormotov_vi.databinding.FragmentUserCommentsBinding
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_comments.recycler.CommentAdapter
import com.bormotov_vi.presentation.users_comments.viewModel.ScreenState
import com.bormotov_vi.presentation.users_comments.viewModel.UserCommentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserCommentsFragment : BaseFragment() {

    private val binding: FragmentUserCommentsBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: CommentAdapter? = null
    private val viewModel: UserCommentsViewModel by viewModel()
    private val args: UserCommentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        lifecycleScope.launchWhenCreated {
            viewModel.beforeReceiveComments(args.postId)
            viewModel.comments.collect {
                when (it) {
                    is ScreenState.Loading -> {
                        binding.commentsFragmentProgressBar.visibility = View.VISIBLE
                    }
                    is ScreenState.Success -> {
                        binding.commentsFragmentProgressBar.visibility = View.GONE
                        adapter = CommentAdapter(it.comments)
                        binding.commentsRecyclerView.adapter = adapter
                    }
                }
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