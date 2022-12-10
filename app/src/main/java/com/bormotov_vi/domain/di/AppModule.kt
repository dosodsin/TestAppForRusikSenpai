package com.bormotov_vi.domain.di

import com.bormotov_vi.data.UsersRepositoryImpl
import com.bormotov_vi.domain.user_interactor.UserRepository
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.domain.user_interactor.UsersInteractorImpl
import com.bormotov_vi.presentation.users_albums.viewModel.UserAlbumViewModel
import com.bormotov_vi.presentation.users_comments.viewModel.UserCommentsViewModel
import com.bormotov_vi.presentation.users_photos.viewModel.UserPhotosViewModel
import com.bormotov_vi.presentation.users_posts.viewModel.PostItemViewModel
import com.bormotov_vi.presentation.users_screen.viewModel.UserItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UsersRepositoryImpl() }
    single<UsersInteractor> { UsersInteractorImpl(get()) }
    viewModel { UserItemsViewModel(get()) }
    viewModel { PostItemViewModel(get()) }
    viewModel { UserPhotosViewModel(get()) }
    viewModel { UserCommentsViewModel(get()) }
    viewModel { UserAlbumViewModel(get()) }

}