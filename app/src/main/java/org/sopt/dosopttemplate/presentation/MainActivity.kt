package org.sopt.dosopttemplate.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelableData

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initView() {
        intent.getParcelableData("user", User::class.java)?.let {
            binding.tvProfileNickname.text = it.nickname
            binding.tvIdContent.text = it.id
            binding.tvMbtiContent.text = it.mbti.toString()
        }
    }

    override fun initEvent() {}
}