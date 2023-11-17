package org.sopt.dosoptkwanheejo.presentation.detail

import org.sopt.dosoptkwanheejo.base.BaseActivity
import org.sopt.dosoptkwanheejo.databinding.ActivityHomeDetailBinding

class HomeDetailActivity :
    BaseActivity<ActivityHomeDetailBinding>(ActivityHomeDetailBinding::inflate) {
    override fun initView() {
    }


    override fun initEvent() {
        initBack()
    }

    private fun initBack() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
