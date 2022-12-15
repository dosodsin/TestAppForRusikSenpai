package com.bormotov_vi.presentation.users_photos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPhotosViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    val photos: LiveData<List<Photo>> get() = _photos
    private val _photos = MutableLiveData<List<Photo>>()

    fun receivePhotos(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _photos.postValue(interactor.receivePhotos(albumId))
        }
    }
}