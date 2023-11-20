package org.sopt.dosoptkwanheejo.presentation.signup

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosoptkwanheejo.DoSoptApp.Companion.getApiHelperInstance
import org.sopt.dosoptkwanheejo.R
import org.sopt.dosoptkwanheejo.base.BaseActivity
import org.sopt.dosoptkwanheejo.databinding.ActivitySignUpBinding
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.SignUpResp
import org.sopt.dosoptkwanheejo.presentation.signup.viewmodel.SignUpViewModel
import org.sopt.dosoptkwanheejo.repository.AuthRepository
import org.sopt.dosoptkwanheejo.util.AuthViewModelFactory
import org.sopt.dosoptkwanheejo.util.MBTI
import org.sopt.dosoptkwanheejo.util.hideKeyboard
import org.sopt.dosoptkwanheejo.util.showShortSnackBar
import org.sopt.dosoptkwanheejo.util.showShortToastMessage
import org.sopt.dosoptkwanheejo.util.toMBTI

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