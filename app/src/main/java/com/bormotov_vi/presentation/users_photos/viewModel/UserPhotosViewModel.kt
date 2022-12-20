package com.bormotov_vi.presentation.users_photos.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserPhotosViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos.asStateFlow()

    fun receivePhotos(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _photos.emit(interactor.receivePhotos(albumId))
        }
    }
}