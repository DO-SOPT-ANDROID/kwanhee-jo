package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.db.local.PreferenceManager
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.ID
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.MBTI
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.NICKNAME
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.sharedPreferencesInstance

class MyPageFragment : BaseFragment<FragmentMypageBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_mypage
    private val preferenceManager by lazy {
        PreferenceManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMyData()
        logout()
    }

    private fun initMyData() {
        sharedPreferencesInstance.run {
            binding.tvIdContent.text = getString(ID, "")
            binding.tvProfileNickname.text = getString(NICKNAME, "")
            binding.tvMbtiContent.text = getString(MBTI, "")
        }
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            preferenceManager.setAutoLogin(false)
            activity?.finish()
        }
    }

    companion object {
        fun newInstance(): MyPageFragment = MyPageFragment()
    }
}