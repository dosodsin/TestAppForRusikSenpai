package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.data.UsersRepositoryImpl
import com.bormotov_vi.domain.model.user.UsersItem

class UsersInteractorImpl(
    private val repository: UsersRepository = UsersRepositoryImpl()
) : UsersInteractor {
    override fun receiveUsers(callback: (List<UsersItem>) -> Unit) = repository.receiveUsers(callback)
}