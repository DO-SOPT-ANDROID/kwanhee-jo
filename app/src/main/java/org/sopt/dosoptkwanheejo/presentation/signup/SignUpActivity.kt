package org.sopt.dosoptkwanheejo.presentation.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import org.sopt.dosoptkwanheejo.view.SoptEditView
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        addIdTextChangedListener()
        addPasswordTextChangedListener()
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

    private fun addIdTextChangedListener() {
        binding.soptEvId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                signUpViewModel.idFlag.value = ID_REGEX.matcher(s).matches()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun addPasswordTextChangedListener() {
        binding.soptEvPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                signUpViewModel.passwordFlag.value = PASSWORD_REGEX.matcher(s).matches()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
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
                    setCustomEditContent(binding.soptEvId, true)
                    setCustomEditContent(binding.soptEvPwd, true)
                    binding.btSignUp.isEnabled = false
                    binding.root.showShortSnackBar(it.message)
                    hideKeyboard(binding.root)
                }
            }
        }
        signUpViewModel.idFlag.observe(this) {
            setCustomEditContent(binding.soptEvId, false)
            binding.btSignUp.isEnabled = it && signUpViewModel.passwordFlag.value == true
        }
        signUpViewModel.passwordFlag.observe(this) {
            setCustomEditContent(binding.soptEvPwd, false)
            binding.btSignUp.isEnabled = it && signUpViewModel.idFlag.value == true
        }
    }

    private fun setCustomEditContent(editView: SoptEditView, visible: Boolean) {
        editView.isVisibleError(visible)
        if (visible) {
            editView.setBackgroundTint(R.color.color_f44336)
        } else {
            editView.setBackgroundTint(R.color.black)
        }
    }

    companion object {
        private const val ID_PATTERN = "^[a-zA-Z0-9]{6,10}$"
        private const val PASSWORD_PATTERN =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,12}$"
        val ID_REGEX: Pattern = Pattern.compile(ID_PATTERN)
        val PASSWORD_REGEX: Pattern = Pattern.compile(PASSWORD_PATTERN)
    }
}