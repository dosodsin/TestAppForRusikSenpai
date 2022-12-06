package com.bormotov_vi.presentation.users_photos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserPhotosViewModel : ViewModel() {

    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    val photos = MutableLiveData<List<Photo>>()

    private fun receivePhotos(albumId: Int) {
        interactor.receivePhotos(albumId) { photoItems ->
            photos.postValue(photoItems)
        }
    }

    fun init(albumId: Int) {
        receivePhotos(albumId)
    }

}