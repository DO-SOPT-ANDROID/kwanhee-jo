package org.sopt.dosopttemplate.presentation.detail

import androidx.core.view.isVisible
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityHomeDetailBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_BIRTH
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_DESCRIPTION
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_IMAGE
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_MUSIC
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_NAME
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeDetailActivity :
    BaseActivity<ActivityHomeDetailBinding>(ActivityHomeDetailBinding::inflate) {
    override fun initView() {
        setDetailUI()
    }

    private fun setDetailUI() {
        intent.run {
            binding.tvDetailProfileName.text = getStringExtra(PROFILE_NAME)
            binding.tvDetailProfileDescription.text = getStringExtra(PROFILE_DESCRIPTION)
            binding.ivDetailProfileBirthday.isVisible = getBooleanExtra(PROFILE_BIRTH, false)
            getIntExtra(PROFILE_IMAGE, 0).let {
                with(binding.ivDetailProfile) {
                    if (it == 0) {
                        roundedCornerGlide(this, R.drawable.kakao_profile, 400, 50)
                    } else {
                        roundedCornerGlide(this, it, 400, 50)
                    }
                }
            }
            getStringExtra(PROFILE_MUSIC).let {
                with(binding.tvStatusMessage) {
                    isVisible = !it.isNullOrEmpty()
                    text = it
                }
            }
        }
    }


    override fun initEvent() {
    }
}
