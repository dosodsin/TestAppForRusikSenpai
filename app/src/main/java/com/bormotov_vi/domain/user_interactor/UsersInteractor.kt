package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.user.UsersItem

interface UsersInteractor {
    fun receiveUsers(callback: (List<UsersItem>) -> Unit)
}