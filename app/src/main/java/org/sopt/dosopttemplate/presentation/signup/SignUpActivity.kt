package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.DoSoptApp.Companion.getApiHelperInstance
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.db.local.PreferenceManager
import org.sopt.dosopttemplate.model.dto.resp.auth.SignUpResp
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        signUp()
        observeData()
    }

    private fun initViewModel() {
        signUpViewModel =
            ViewModelProvider(
                this,
                AuthViewModelFactory(AuthRepository(getApiHelperInstance()))
            ).get(SignUpViewModel::class.java)
    }

    private fun signUp() {
        binding.btSignUp.setOnClickListener {
            hideKeyboard(binding.root)
            if (isValidInformation()) {
                signUpViewModel.signUp(
                    id = binding.soptEvId.getEditText(),
                    nickname = binding.soptEvNickname.getEditText(),
                    password = binding.soptEvPwd.getEditText()
                )
            }
        }
    }

    private fun isValidInformation(): Boolean =
        binding.soptEvId.getEditText().length in 6..10
        && binding.soptEvPwd.getEditText().length in 8..12
        && binding.soptEvNickname.getEditText().isNotEmpty()
        && binding.soptEvMbti.getEditText().toMBTI() != MBTI.ERROR

    private fun observeData() {
        signUpViewModel.signUpResp.observe(this) {
            when (it) {
                is SignUpResp.Success -> {
                    showShortToastMessage(getString(R.string.success_sign_up))
                    intent.putExtra(
                        PreferenceManager.MBTI,
                        binding.soptEvMbti.getEditText().uppercase()
                    )
                    setResult(RESULT_OK, intent)
                    finish()
                }

                is SignUpResp.Error -> {
                    binding.root.showShortSnackBar(it.message)
                    hideKeyboard(binding.root)
                }
            }
        }
    }
}