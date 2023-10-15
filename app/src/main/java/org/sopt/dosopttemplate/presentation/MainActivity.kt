package org.sopt.dosopttemplate.presentation

import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.ID
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.MBTI
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.NICKNAME
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.getSharedPreferenceUser

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initView() {
        with(getSharedPreferenceUser()) {
            binding.tvIdContent.text = getString(ID, "")
            binding.tvProfileNickname.text = getString(NICKNAME, "")
            binding.tvMbtiContent.text = getString(MBTI, "")
        }
    }

    override fun initEvent() {
        logout()
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            getSharedPreferenceUser().edit().clear().apply()
            finish()
        }
    }
}