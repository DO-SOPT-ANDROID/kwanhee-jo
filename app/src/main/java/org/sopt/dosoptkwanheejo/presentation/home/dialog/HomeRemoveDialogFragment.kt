package org.sopt.dosoptkwanheejo.presentation.home.dialog

import android.os.Bundle
import android.view.View
import org.sopt.dosoptkwanheejo.R
import org.sopt.dosoptkwanheejo.base.BaseDialogFragment
import org.sopt.dosoptkwanheejo.databinding.DialogFragmentHomeRemoveBinding

class HomeRemoveDialogFragment : BaseDialogFragment<DialogFragmentHomeRemoveBinding>() {
    override val layoutResId: Int
        get() = R.layout.dialog_fragment_home_remove

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
        binding.btnRemove.setOnClickListener {
        }
    }

    private fun initBackgroundDismiss() {
        binding.dialogContainer.setOnClickListener { dismiss() }
    }
}