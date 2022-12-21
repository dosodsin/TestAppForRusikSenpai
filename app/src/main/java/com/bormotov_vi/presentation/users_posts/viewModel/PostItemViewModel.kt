package com.bormotov_vi.presentation.users_posts.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostItemViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _posts = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val posts: StateFlow<ScreenState> = _posts.asStateFlow()

    fun beforeReceivePosts(userId: Int) {
        _posts.value = ScreenState.Loading
        receivePosts(userId)
    }

    private fun receivePosts(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            _posts.value = ScreenState.Success(interactor.receivePosts(userId))
        }
    }
}

sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
    object Error : ScreenState()
    data class Success(val posts: List<UserPostItem>) : ScreenState()
}