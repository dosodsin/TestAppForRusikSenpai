package com.bormotov_vi.presentation.users_comments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserCommentsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _comments = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val comments: StateFlow<ScreenState> = _comments.asStateFlow()

    fun beforeReceiveComments(postId: Int){
        _comments.value = ScreenState.Loading
        receiveComments(postId)
    }

    private fun receiveComments(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            _comments.value = ScreenState.Success(interactor.receiveComments(postId))
        }
    }
}

sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
    object Error : ScreenState()
    data class Success(val comments: List<Comment>) : ScreenState()
}