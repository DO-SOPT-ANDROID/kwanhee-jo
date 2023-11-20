package org.sopt.dosopttemplate.presentation.home.dialog

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseDialogFragment
import org.sopt.dosopttemplate.databinding.DialogFragmentHomeAddBinding
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar

class HomeAddDialogFragment : BaseDialogFragment<DialogFragmentHomeAddBinding>() {
    override val layoutResId: Int
        get() = R.layout.dialog_fragment_home_add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSetting()
    }

    private fun initSetting() {
        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentFullScreen)
        isCancelable = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackgroundDismiss()
        addProfile()
        binding.tvBirth.setOnClickListener {
            binding.soptNumberPicker.isVisible = !binding.soptNumberPicker.isVisible
        }
    }

    private fun addProfile() {
        binding.btnAdd.setOnClickListener {
            if (binding.evNickname.text.isNullOrEmpty()) {
                binding.evNickname.showShortSnackBar(getString(R.string.set_nickname))
                activity?.hideKeyboard(binding.root)
            } else if (!binding.soptNumberPicker.isVisible) {
                binding.soptNumberPicker.showShortSnackBar(getString(R.string.set_birth))
            } else {
                dismiss()
            }
        }
    }

    private fun initBackgroundDismiss() {
        binding.dialogContainer.setOnClickListener { dismiss() }
    }
}