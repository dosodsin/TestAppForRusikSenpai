package com.bormotov_vi.presentation.users_screen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserItemsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    val users = MutableLiveData<List<UsersItem>>()

    private fun receiveUsers() {
        interactor.receiveUsers { userItems ->
            users.postValue(userItems)
        }
    }

    init {
        receiveUsers()
    }

}