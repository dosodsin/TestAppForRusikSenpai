package com.bormotov_vi.presentation.base_fragment

import android.view.KeyEvent
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bormotov_vi.R


abstract class BaseFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        view?.setOnKeyListener { v, keyCode, event ->
            if (event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                onBackPressed()
            }
            return@setOnKeyListener false
        }
    }

    protected fun navigate(to: String, fragment: BaseFragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activityMain, fragment)
            .addToBackStack(to)
            .commit()
    }

    protected fun moveToPrevFragmentByToolbarBackImage() {
        val backImage = activity?.findViewById<ImageView>(R.id.backImage)
        backImage?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun onBackPressed(): Boolean {
        activity?.onBackPressed()
        return true
    }
}