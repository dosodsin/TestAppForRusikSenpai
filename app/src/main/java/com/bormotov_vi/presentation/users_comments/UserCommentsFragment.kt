package com.bormotov_vi.presentation.users_comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentUserCommentsBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.users_comments.recycler.CommentAdapter
import com.bormotov_vi.presentation.users_posts.UserPostsFragment
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment


class UserCommentsFragment : Fragment() {

    private var binding: FragmentUserCommentsBinding? = null
    private var adapter: CommentAdapter? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserCommentsBinding.inflate(inflater, container, false)
        val imageBack = binding?.toolbar?.backImage
        interactor.receiveComments {
            adapter = CommentAdapter(it)
            binding?.commentsRecyclerView?.adapter = adapter
        }

        imageBack?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityMain, UserPostsFragment.newInstance())
                .addToBackStack("UserPostsFragment")
                .commit()
        }

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserCommentsFragment()
    }
}