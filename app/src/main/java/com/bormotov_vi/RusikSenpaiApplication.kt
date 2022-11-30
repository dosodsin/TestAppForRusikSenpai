package com.bormotov_vi

import android.app.Application
import com.bormotov_vi.domain.user_interactor.UsersInteractorImpl

class RusikSenpaiApplication : Application() {

    companion object {
        val interactor = UsersInteractorImpl()
    }
}