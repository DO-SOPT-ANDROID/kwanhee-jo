package org.sopt.dosopttemplate.presentation.home.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseDialogFragment
import org.sopt.dosopttemplate.databinding.DialogFragmentHomeAddBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.toProfileBirth
import java.sql.Timestamp

class HomeAddDialogFragment : BaseDialogFragment<DialogFragmentHomeAddBinding>() {
    override val layoutResId: Int
        get() = R.layout.dialog_fragment_home_add
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
        addProfile()
    }

    private fun addProfile() {
        binding.btnAdd.setOnClickListener {
            if (binding.soptEvNickname.getEditText().isEmpty() ||
                binding.soptEvBirth.getEditText().isEmpty()
            ) {
                activity?.hideKeyboard(binding.root)
                binding.root.showShortSnackBar(getString(R.string.fail))
            } else {
                val newProfile = HomeProfileModel.Profile(
                    id = homeViewModel.profileList.value?.size ?: 0,
                    name = binding.soptEvNickname.getEditText(),
                    description = binding.soptEvDescription.getEditText(),
                    birth = Timestamp.valueOf(binding.soptEvBirth.getEditText() + " 00:00:00").time,
                    update = System.currentTimeMillis()
                )
                homeViewModel.addProfileList(newProfile)
                if (newProfile.checkBirthDay()) {
                    homeViewModel.addBirthdayProfile(newProfile.toProfileBirth())
                }
                dismiss()
            }
        }
    }

    private fun initBackgroundDismiss() {
        binding.dialogContainer.setOnClickListener { dismiss() }
    }
}