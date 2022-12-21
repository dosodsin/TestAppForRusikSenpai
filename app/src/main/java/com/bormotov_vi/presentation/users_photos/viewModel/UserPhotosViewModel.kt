package com.bormotov_vi.presentation.users_photos.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserPhotosViewModel(
    private val interactor: UsersInteractor
) : ViewModel() {

    private val _photos = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val photos: StateFlow<ScreenState> = _photos.asStateFlow()

    fun beforeReceivePhotos(albumId: Int){
        _photos.value=ScreenState.Loading
        receivePhotos(albumId)
    }

    private fun receivePhotos(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            _photos.value = ScreenState.Success(interactor.receivePhotos(albumId))
        }
    }
}

sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
    object Error : ScreenState()
    data class Success(val photos: List<Photo>) : ScreenState()
}