package com.bormotov_vi.presentation.base_fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


abstract class BaseFragment : Fragment() {



    protected fun navigate(from: Int, key: String, value: Int) {
        findNavController()
            .navigate(from, bundleOf(key to value))
    }

    protected fun moveToPrevFragmentByToolbarBackImage() {
        findNavController()
            .popBackStack()
    }
}