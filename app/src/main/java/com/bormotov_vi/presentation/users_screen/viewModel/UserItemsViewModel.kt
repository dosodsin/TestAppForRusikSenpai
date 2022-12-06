package com.bormotov_vi.presentation.users_screen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserItemsViewModel : ViewModel() {

    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    val users = MutableLiveData<List<UsersItem>>()

    private fun receiveUsers() {
        interactor.receiveUsers { userItems ->
            users.postValue(userItems)
        }
    }

    fun init() {
        receiveUsers()
    }

}