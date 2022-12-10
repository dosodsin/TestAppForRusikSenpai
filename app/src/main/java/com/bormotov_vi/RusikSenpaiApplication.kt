package com.bormotov_vi

import android.app.Application
import com.bormotov_vi.domain.di.appModule
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RusikSenpaiApplication : Application() {

    val interactor: UsersInteractor by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RusikSenpaiApplication)
            modules(appModule)
        }
    }

}