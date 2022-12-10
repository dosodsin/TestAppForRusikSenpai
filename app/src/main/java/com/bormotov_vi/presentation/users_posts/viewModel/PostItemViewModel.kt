package com.bormotov_vi.presentation.users_posts.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class PostItemViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    val posts = MutableLiveData<List<UserPostItem>>()
    var userId: Int? = null

    private fun receivePosts() {
        interactor.receivePosts(userId) { postItems ->
            posts.postValue(postItems)
        }
    }

    init {
        receivePosts()
    }

}