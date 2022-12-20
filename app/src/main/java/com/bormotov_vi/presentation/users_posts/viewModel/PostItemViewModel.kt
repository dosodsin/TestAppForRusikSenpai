package com.bormotov_vi.presentation.users_posts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostItemViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _posts = MutableStateFlow<List<UserPostItem>>(emptyList())
    val posts: StateFlow<List<UserPostItem>> = _posts.asStateFlow()


    fun receivePosts(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.emit(interactor.receivePosts(userId))
        }
    }
}