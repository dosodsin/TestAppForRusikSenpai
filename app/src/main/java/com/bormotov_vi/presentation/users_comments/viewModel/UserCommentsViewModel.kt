package com.bormotov_vi.presentation.users_comments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserCommentsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    fun receiveComments(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _comments.emit(interactor.receiveComments(postId))
        }
    }
}