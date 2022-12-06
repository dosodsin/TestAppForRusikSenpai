package com.bormotov_vi.presentation.users_posts.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class PostItemViewModel : ViewModel() {

    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    val posts = MutableLiveData<List<UserPostItem>>()

    private fun receivePosts(userId: Int) {
        interactor.receivePosts(userId) { postItems ->
            posts.postValue(postItems)
        }
    }

    fun init(userId: Int) {
        receivePosts(userId)
    }

}