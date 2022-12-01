package com.bormotov_vi.presentation.users_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.R
import com.bormotov_vi.databinding.ActivityMainBinding

class UsersActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val mainFragment = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activityMain, mainFragment)
            .commit()

    }
}