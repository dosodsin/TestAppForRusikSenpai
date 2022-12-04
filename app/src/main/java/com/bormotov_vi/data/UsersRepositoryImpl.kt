package com.bormotov_vi.data

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.retrofit.RetrofitInstance.api
import com.bormotov_vi.domain.user_interactor.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepositoryImpl : UserRepository {

    override fun receiveUsers(callback: (List<UsersItem>) -> Unit) {
        api.getUsers().enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful) {
                    callback(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {

            }
        })
    }

    override fun receivePosts(userId: Int?, callback: (List<UserPostItem>) -> Unit) {
        api.getPosts().enqueue(object : Callback<List<UserPostItem>> {
            override fun onResponse(
                call: Call<List<UserPostItem>>,
                response: Response<List<UserPostItem>>
            ) {
                if (response.isSuccessful) {
                    var body = response.body()
                    body = body?.filter {
                        it.userId == userId
                    }
                    if (body != null) {
                        callback(body)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserPostItem>>, t: Throwable) {

            }
        })
    }

    override fun receiveComments(postId: Int?, callback: (List<Comment>) -> Unit) {
        api.getComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(
                call: Call<List<Comment>>,
                response: Response<List<Comment>>
            ) {
                if (response.isSuccessful) {
                    var body = response.body()
                    body = body?.filter {
                        it.postId == postId
                    }
                    if (body != null) {
                        callback(body)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

            }
        })
    }

    override fun receiveAlbums(userId: Int?, callback: (List<Album>) -> Unit) {
        api.getAlbums().enqueue(object : Callback<List<Album>> {
            override fun onResponse(
                call: Call<List<Album>>,
                response: Response<List<Album>>
            ) {
                if (response.isSuccessful) {
                    var body = response.body()
                    body = body?.filter {
                        it.userId == userId
                    }
                    if (body != null) {
                        callback(body)
                    }
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {

            }
        })
    }

    override fun receivePhotos(albumId: Int?, callback: (List<Photo>) -> Unit) {
        api.getPhotos().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(
                call: Call<List<Photo>>,
                response: Response<List<Photo>>
            ) {
                if (response.isSuccessful) {
                    var body = response.body()
                    body = body?.filter {
                        it.albumId == albumId
                    }
                    if (body != null) {
                        callback(body)
                    }
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }
        })
    }
}