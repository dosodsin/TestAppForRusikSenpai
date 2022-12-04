package com.bormotov_vi.presentation.base_fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bormotov_vi.R


open class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onResume() {
        super.onResume()
        view?.setOnKeyListener { v, keyCode, event ->
            if (event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                onBackPressed()
            }
            return@setOnKeyListener false
        }
    }

    private fun onBackPressed():Boolean {
        activity?.onBackPressed()
        return true
    }
}