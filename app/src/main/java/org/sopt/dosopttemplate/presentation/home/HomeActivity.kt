package org.sopt.dosopttemplate.presentation

import androidx.activity.OnBackPressedCallback
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.AUTO_LOGIN
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.ID
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.MBTI
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.NICKNAME
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.getSharedPreferenceUser
import org.sopt.dosopttemplate.util.showShortToastMessage

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    private var backPressedTime = 0L

    override fun initView() {
        initSharedPreference()

    }

    private fun initSharedPreference() {
        with(getSharedPreferenceUser()) {
            binding.tvIdContent.text = getString(ID, "")
            binding.tvProfileNickname.text = getString(NICKNAME, "")
            binding.tvMbtiContent.text = getString(MBTI, "")
        }
    }

    override fun initEvent() {
        logout()
        initBackPressed()
    }

    private fun initBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime >= 2000L) {
                    backPressedTime = System.currentTimeMillis()
                    this@HomeActivity.showShortToastMessage(getString(R.string.finish_dialog))
                } else {
                    finish()
                }
            }
        })
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            getSharedPreferenceUser().edit().putBoolean(AUTO_LOGIN, false).apply()
            finish()
        }
    }
}