package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.user.UsersItem

interface UsersRepository {
    fun receiveUsers(callback: (List<UsersItem>) -> Unit)
}