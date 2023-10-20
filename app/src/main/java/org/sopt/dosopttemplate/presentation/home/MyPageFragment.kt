package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.AUTO_LOGIN
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.ID
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.MBTI
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.NICKNAME
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.getSharedPreferenceUser

class MyPageFragment : BaseFragment<FragmentMypageBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_mypage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyData()
        logout()
    }

    private fun initMyData() {
        activity?.getSharedPreferenceUser()?.run {
            binding.tvIdContent.text = this.getString(ID, "")
            binding.tvProfileNickname.text = this.getString(NICKNAME, "")
            binding.tvMbtiContent.text = this.getString(MBTI, "")
        }
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            activity?.apply {
                getSharedPreferenceUser().edit(commit = true) {
                    putBoolean(AUTO_LOGIN, false)
                }
                finish()
            }
        }
    }

    companion object {
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }
}