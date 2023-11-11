package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.db.local.PreferenceManager

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
        preferenceManager.run {
            binding.tvIdContent.text = getId()
            binding.tvProfileNickname.text = getNickname()
            binding.tvMbtiContent.text = getMBTI()
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