package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.db.local.Preference
import org.sopt.dosopttemplate.db.local.Preference.Companion.EXTRA_USER
import org.sopt.dosopttemplate.db.local.Preference.Companion.ID
import org.sopt.dosopttemplate.db.local.Preference.Companion.PWD
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.presentation.SignUpActivity
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.util.getParcelableData
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToastMessage

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private val preference: Preference by lazy {
        Preference(this)
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                preference.setUser(it.data?.getParcelableData(EXTRA_USER, User::class.java))
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
        autoLogin()
    }

    private fun autoLogin() {
        if (preference.getAutoLogin()) {
            goToMainActivity()
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
                checkLoginInfo(binding.soptEvId.getEditText(), binding.soptEvPwd.getEditText())
            }
        }
    }

    private fun initSignUp() {
        binding.btSignUp.setOnClickListener {
            goToSignUpActivity()
        }
    }

    private fun checkLoginInfo(id: String, pwd: String) {
        preference.run {
            if (id == getId() && pwd == getPassword()) {
                successLogin()
            } else {
                failLogin()
            }
        }
    }

    private fun successLogin() {
        preference.setAutoLogin(true)
        showShortToastMessage(getString(R.string.success_login))
        goToMainActivity()
    }

    private fun failLogin() {
        showShortToastMessage(getString(R.string.fail_login))
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