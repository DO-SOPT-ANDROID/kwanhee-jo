package org.sopt.dosopttemplate.base

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import org.sopt.dosopttemplate.util.showShortSnackBar

abstract class BaseDialogFragment<T : ViewDataBinding> : DialogFragment() {
    @get:LayoutRes
    abstract val layoutResId: Int

    private var _binding: T? = null

    val binding: T
        get() = requireNotNull(_binding) { "DialogFragment binding failed" }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}