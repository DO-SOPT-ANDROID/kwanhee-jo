package org.sopt.dosopttemplate.presentation.home.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseDialogFragment
import org.sopt.dosopttemplate.databinding.DialogFragmentHomeRemoveBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.BUNDLE_ITEM_ID_KEY
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.REQUEST_ITEM_ID_KEY
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel

class HomeRemoveDialogFragment : BaseDialogFragment<DialogFragmentHomeRemoveBinding>() {
    override val layoutResId: Int
        get() = R.layout.dialog_fragment_home_remove
    private val homeViewModel: HomeViewModel by activityViewModels()

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
            setFragmentResultListener(REQUEST_ITEM_ID_KEY) { _, bundle ->
                homeViewModel.removeProfileList(bundle.getInt(BUNDLE_ITEM_ID_KEY))
            }
            this@HomeRemoveDialogFragment.dismiss()
        }
    }

    private fun initBackgroundDismiss() {
        binding.dialogContainer.setOnClickListener { dismiss() }
    }
}