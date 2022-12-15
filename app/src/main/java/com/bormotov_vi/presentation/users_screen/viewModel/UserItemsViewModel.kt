package com.bormotov_vi.presentation.users_screen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserItemsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    val users: LiveData<List<UsersItem>> get() = _users
    private val _users = MutableLiveData<List<UsersItem>>()

    private fun receiveUsers() {
        viewModelScope.launch(Dispatchers.IO){
            _users.postValue(interactor.receiveUsers())
        }
    }

    init {
        receiveUsers()
    }

}