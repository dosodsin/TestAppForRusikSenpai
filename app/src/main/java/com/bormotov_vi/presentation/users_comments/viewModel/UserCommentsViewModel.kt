package com.bormotov_vi.presentation.users_comments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserCommentsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {


    val comments: LiveData<List<Comment>> get() = _comments
    private val _comments = MutableLiveData<List<Comment>>()

    fun receiveComments(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _comments.postValue(interactor.receiveComments(postId))
        }
    }
}