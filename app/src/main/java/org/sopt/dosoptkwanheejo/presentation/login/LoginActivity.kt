package org.sopt.dosoptkwanheejo.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosoptkwanheejo.DoSoptApp
import org.sopt.dosoptkwanheejo.DoSoptApp.Companion.ID_REGEX
import org.sopt.dosoptkwanheejo.DoSoptApp.Companion.PASSWORD_REGEX
import org.sopt.dosoptkwanheejo.DoSoptApp.Companion.sharedPreferencesInstance
import org.sopt.dosoptkwanheejo.R
import org.sopt.dosoptkwanheejo.base.BaseActivity
import org.sopt.dosoptkwanheejo.databinding.ActivityLoginBinding
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager.Companion.AUTO_LOGIN
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager.Companion.ID
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager.Companion.MBTI
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager.Companion.PWD
import org.sopt.dosoptkwanheejo.model.dto.RespResult
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.LoginResp
import org.sopt.dosoptkwanheejo.presentation.home.HomeActivity
import org.sopt.dosoptkwanheejo.presentation.login.viewmodel.LoginViewModel
import org.sopt.dosoptkwanheejo.presentation.signup.SignUpActivity
import org.sopt.dosoptkwanheejo.repository.AuthRepository
import org.sopt.dosoptkwanheejo.util.AuthViewModelFactory
import org.sopt.dosoptkwanheejo.util.hideKeyboard
import org.sopt.dosoptkwanheejo.util.showShortSnackBar
import org.sopt.dosoptkwanheejo.util.showShortToastMessage
import org.sopt.dosoptkwanheejo.view.SoptEditView

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var loginViewModel: LoginViewModel

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                PreferenceManager().setMBTI(it.data?.getStringExtra(MBTI).toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding.soptEvId.setInputType(InputType.TYPE_CLASS_TEXT)
        binding.soptEvPwd.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        autoLogin()
        initHideKeyboard()
        initLogin()
        initSignUp()
        addIdTextChangedListener()
        addPasswordTextChangedListener()
        observeData()
    }

    private fun addIdTextChangedListener() {
        binding.soptEvId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.idFlag.value = ID_REGEX.matcher(s).matches()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun addPasswordTextChangedListener() {
        binding.soptEvPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.passwordFlag.value = PASSWORD_REGEX.matcher(s).matches()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ID, binding.soptEvId.getEditText())
        outState.putString(PWD, binding.soptEvPwd.getEditText())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.soptEvId.setEditHintText(savedInstanceState.getString(ID) ?: "")
        binding.soptEvPwd.setEditHintText(savedInstanceState.getString(PWD) ?: "")
    }

    private fun initViewModel() {
        loginViewModel =
            ViewModelProvider(
                this,
                AuthViewModelFactory(AuthRepository(DoSoptApp.getApiHelperInstance()))
            ).get(LoginViewModel::class.java)
    }

    private fun autoLogin() {
        if (sharedPreferencesInstance.getBoolean(AUTO_LOGIN, false)) {
            goToMainActivity()
        }
    }

    private fun initHideKeyboard() {
        binding.loginLayoutContainer.setOnClickListener {
            hideKeyboard(binding.root)
        }
    }

    private fun initLogin() {
        binding.btLogin.setOnClickListener {
            if (binding.soptEvId.getEditText().isEmpty()) {
                binding.soptEvId.showShortSnackBar(getString(R.string.input_id))
            } else if (binding.soptEvPwd.getEditText().isEmpty()) {
                binding.soptEvId.showShortSnackBar(getString(R.string.input_pwd))
            } else {
                loginViewModel.login(
                    binding.soptEvId.getEditText(),
                    binding.soptEvPwd.getEditText(),
                    binding.switchAutoLogin.isChecked
                )
            }
        }
    }

    private fun initSignUp() {
        binding.btSignUp.setOnClickListener {
            goToSignUpActivity()
        }
    }

    private fun successLogin() {
        showShortToastMessage(getString(R.string.success_login))
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivityForResult.launch(intent)
        finish()
    }

    private fun goToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult.launch(intent)
    }

    private fun observeData() {
        loginViewModel.loginResp.observe(this) {
            when (it) {
                is LoginResp -> {
                    successLogin()
                }

                is RespResult -> {
                    setCustomEditContent(binding.soptEvId, true)
                    setCustomEditContent(binding.soptEvPwd, true)
                    binding.root.showShortSnackBar(it.message)
                    hideKeyboard(binding.root)
                }
            }
        }
        loginViewModel.idFlag.observe(this) {
            setCustomEditContent(binding.soptEvId, false)
            binding.btLogin.isEnabled = it && loginViewModel.passwordFlag.value == true
        }
        loginViewModel.passwordFlag.observe(this) {
            setCustomEditContent(binding.soptEvPwd, false)
            binding.btLogin.isEnabled = it && loginViewModel.idFlag.value == true
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
}