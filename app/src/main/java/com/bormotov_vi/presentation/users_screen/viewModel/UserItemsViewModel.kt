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

    private val _users = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val users: StateFlow<ScreenState> = _users.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            //TODO
            // _users.emit(interactor.receiveUsers())
        }
    }
}

sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
    object Error : ScreenState()
    data class Success(val users: List<UsersItem>)
}