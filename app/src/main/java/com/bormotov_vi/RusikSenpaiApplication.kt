package com.bormotov_vi

import android.app.Application
import com.bormotov_vi.domain.retrofit.Repository
import com.bormotov_vi.domain.retrofit.RetrofitInstance

class RusikSenpaiApplication : Application() {

    companion object {
        val repository = Repository(RetrofitInstance.api)
    }
}