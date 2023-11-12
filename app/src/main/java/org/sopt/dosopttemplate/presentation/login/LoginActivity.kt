package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.DoSoptApp
import org.sopt.dosopttemplate.DoSoptApp.Companion.sharedPreferencesInstance
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.db.local.PreferenceManager
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.AUTO_LOGIN
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.ID
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.MBTI
import org.sopt.dosopttemplate.db.local.PreferenceManager.Companion.PWD
import org.sopt.dosopttemplate.model.dto.RespResult
import org.sopt.dosopttemplate.model.dto.resp.auth.LoginResp
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.login.viewmodel.LoginViewModel
import org.sopt.dosopttemplate.presentation.signup.SignUpActivity
import org.sopt.dosopttemplate.repository.AuthRepository
import org.sopt.dosopttemplate.util.AuthViewModelFactory
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToastMessage

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var loginViewModel: LoginViewModel

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                PreferenceManager().setMBTI(it.data?.getStringExtra(MBTI).toString())
            }
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

    override fun initView() {
        binding.soptEvId.setInputType(InputType.TYPE_CLASS_TEXT)
        binding.soptEvPwd.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        initViewModel()
        autoLogin()
        observeData()
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

    private fun observeData() {
        loginViewModel.loginResp.observe(this) {
            when (it) {
                is LoginResp -> {
                    successLogin()
                }
                is RespResult -> {
                    binding.root.showShortSnackBar(it.message)
                    hideKeyboard(binding.root)
                }
            }
        }
    }


    override fun initEvent() {
        initHideKeyboard()
        initLogin()
        initSignUp()
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

}