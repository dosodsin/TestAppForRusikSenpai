package com.bormotov_vi.presentation.users_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.databinding.FragmentMainBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.presentation.base_fragment.BaseFragment
import com.bormotov_vi.presentation.users_posts_and_albums.UserPostAndAlbumsFragment
import com.bormotov_vi.presentation.users_screen.recycler.UserAdapter


class MainFragment : BaseFragment() {

    private var binding: FragmentMainBinding? = null
    private var adapter: UserAdapter? = null
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor
    private var bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        interactor.receiveUsers {
            adapter = UserAdapter(it) {
                bundle.putInt("userId", it.id)
                val usersPostsAndAlbumsFragment = UserPostAndAlbumsFragment.newInstance()
                usersPostsAndAlbumsFragment?.arguments = bundle
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityMain, usersPostsAndAlbumsFragment!!)
                    .addToBackStack("usersPostsAndAlbumsFragment")
                    .commit()
            }
            binding?.mainFragmentRecyclerView?.adapter = adapter
        }
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}