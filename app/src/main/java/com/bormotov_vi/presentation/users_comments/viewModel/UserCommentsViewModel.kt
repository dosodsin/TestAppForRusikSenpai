package com.bormotov_vi.presentation.users_comments.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserCommentsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {


    val comments = MutableLiveData<List<Comment>>()
    var postId: Int? = null

    private fun receiveComments() {
        interactor.receiveComments(postId) { commentItems ->
            comments.postValue(commentItems)
        }
    }

    init {
        receiveComments()
    }

}