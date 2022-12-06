package com.bormotov_vi.presentation.users_comments.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserCommentsViewModel : ViewModel() {

    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    val comments = MutableLiveData<List<Comment>>()

    private fun receiveComments(postId: Int) {
        interactor.receiveComments(postId) { commentItems ->
            comments.postValue(commentItems)
        }
    }

    fun init(postId: Int) {
        receiveComments(postId)
    }

}