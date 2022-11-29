package com.bormotov_vi.data

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.user_interactor.UsersRepository
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

private const val URL_USERS = "https://jsonplaceholder.typicode.com/users"
private const val URL_POSTS = "https://jsonplaceholder.typicode.com/posts"
private const val URL_COMMENTS = "https://jsonplaceholder.typicode.com/comments"
private const val URL_ALBUMS = "https://jsonplaceholder.typicode.com/albums"
private const val URL_PHOTOS = "https://jsonplaceholder.typicode.com/photos"

class UsersRepositoryImpl : UsersRepository {
    private var client: OkHttpClient = OkHttpClient()

    override fun receiveUsers(callback: (List<UsersItem>) -> Unit) {
        val request = Request.Builder()
            .url(URL_USERS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<UsersItem>::class.java).toList()
                    callback(result)
                }
            }
        })
    }

    override fun receivePosts(callback: (List<UserPostItem>) -> Unit) {
        val request = Request.Builder()
            .url(URL_POSTS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<UserPostItem>::class.java).toList()
                    callback(result)
                }
            }
        })
    }

    override fun receiveComments(callback: (List<Comment>) -> Unit) {
        val request = Request.Builder()
            .url(URL_COMMENTS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<Comment>::class.java).toList()
                    callback(result)
                }
            }
        })
    }

    override fun receiveAlbums(callback: (List<Album>) -> Unit) {
        val request = Request.Builder()
            .url(URL_ALBUMS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<Album>::class.java).toList()
                    callback(result)
                }
            }
        })
    }

    override fun receivePhotos(callback: (List<Photo>) -> Unit) {
        val request = Request.Builder()
            .url(URL_PHOTOS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<Photo>::class.java).toList()
                    callback(result)
                }
            }
        })
    }
}