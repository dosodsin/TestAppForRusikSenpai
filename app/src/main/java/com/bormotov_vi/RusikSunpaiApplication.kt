package com.bormotov_vi

import android.app.Application
import com.bormotov_vi.domain.retrofit.Repository


class RusikSunpaiApplication : Application() {

    val repository = Repository()

}