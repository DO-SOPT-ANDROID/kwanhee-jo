package org.sopt.dosopttemplate.presentation

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.ID
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.MBTI
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.NICKNAME
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.getSharedPreferenceUser

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
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
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.finish_dialog),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    finish()
                }
            }
        })
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            getSharedPreferenceUser().edit().clear().apply()
            finish()
        }
    }
}