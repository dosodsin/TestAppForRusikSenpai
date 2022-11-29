package com.bormotov_vi.presentation.users_photos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.databinding.ActivityUserPhotosBinding
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import com.bormotov_vi.domain.user_interactor.UsersInteractorImpl
import com.bormotov_vi.presentation.users_photos.recycler.PhotoAdapter

class UserPhotosActivity : AppCompatActivity() {

    private var binding: ActivityUserPhotosBinding? = null
    private var adapter: PhotoAdapter? = null
    private var albumId: Int? = null
    private val interactor: UsersInteractor = UsersInteractorImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPhotosBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val arguments = intent.extras
        albumId = arguments?.getInt("albumId")

        interactor.receivePhotos {
            runOnUiThread {
                adapter = PhotoAdapter(it)
                binding!!.photoRecyclerView.adapter = adapter
            }
        }
    }
}