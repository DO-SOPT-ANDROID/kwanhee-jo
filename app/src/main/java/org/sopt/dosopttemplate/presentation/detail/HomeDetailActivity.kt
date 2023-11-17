package org.sopt.dosopttemplate.presentation.detail

import android.os.Bundle
import org.sopt.dosoptkwanheejo.databinding.ActivityHomeDetailBinding
import org.sopt.dosopttemplate.base.BaseActivity

class HomeDetailActivity :
    BaseActivity<ActivityHomeDetailBinding>(ActivityHomeDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBack()
    }

    private fun initBack() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
