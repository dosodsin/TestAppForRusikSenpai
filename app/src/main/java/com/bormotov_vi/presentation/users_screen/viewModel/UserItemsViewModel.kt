package com.bormotov_vi.presentation.users_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserItemsViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _users = MutableStateFlow<List<UsersItem>>(emptyList())
    val users: StateFlow<List<UsersItem>> = _users.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _users.emit(interactor.receiveUsers())
        }
    }

}