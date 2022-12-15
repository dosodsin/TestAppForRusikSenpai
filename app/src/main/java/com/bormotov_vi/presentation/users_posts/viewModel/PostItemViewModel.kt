package com.bormotov_vi.presentation.users_posts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostItemViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    val posts: LiveData<List<UserPostItem>> get() = _posts
    private val _posts = MutableLiveData<List<UserPostItem>>()

    fun receivePosts(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.postValue(interactor.receivePosts(userId))
        }
    }
}