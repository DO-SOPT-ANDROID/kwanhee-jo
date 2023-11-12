package org.sopt.dosopttemplate.presentation.signup

import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.DoSoptApp.Companion.getApiHelperInstance
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.model.dto.resp.SignUpResp
import org.sopt.dosopttemplate.presentation.signup.viewmodel.SignUpViewModel
import org.sopt.dosopttemplate.repository.AuthRepository
import org.sopt.dosopttemplate.util.AuthViewModelFactory
import org.sopt.dosopttemplate.util.MBTI
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToastMessage
import org.sopt.dosopttemplate.util.toMBTI

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    private lateinit var signUpViewModel: SignUpViewModel

    override fun initView() {
        initViewModel()
        observeData()
    }

    private fun initViewModel() {
        signUpViewModel =
            ViewModelProvider(
                this,
                AuthViewModelFactory(AuthRepository(getApiHelperInstance()))
            ).get(SignUpViewModel::class.java)
    }

    private fun observeData() {
        signUpViewModel.signUpResp.observe(this) {
            when (it) {
                is SignUpResp.Success -> {
                    showShortToastMessage(getString(R.string.success_sign_up))
                    setResult(RESULT_OK, intent)
                    finish()
                }

                is SignUpResp.Error -> {
                    binding.root.showShortSnackBar(getString(R.string.fail_sign_up))
                }
            }
        }
    }

    override fun initEvent() {
        initDoSignUp()
    }

    private fun initDoSignUp() {
        binding.btSignUp.setOnClickListener {
            hideKeyboard(binding.root)
            if (
                binding.soptEvId.getEditText().length in 6..10
                && binding.soptEvPwd.getEditText().length in 8..12
                && binding.soptEvNickname.getEditText().isNotEmpty()
                && binding.soptEvMbti.getEditText().toMBTI() != MBTI.ERROR
            ) {
                signUpViewModel.signUp(
                    id = binding.soptEvId.getEditText(),
                    nickname = binding.soptEvNickname.getEditText(),
                    password = binding.soptEvPwd.getEditText()
                )
            }
        }
    }
}