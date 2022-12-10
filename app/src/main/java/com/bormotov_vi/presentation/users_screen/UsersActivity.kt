package com.bormotov_vi.presentation.users_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.R
import com.bormotov_vi.databinding.ActivityMainBinding

class UsersActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val mainFragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activityMain, mainFragment)
            .addToBackStack(MAIN_FRAGMENT_TAG)
            .commit()

    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "mainFragment"
    }
}