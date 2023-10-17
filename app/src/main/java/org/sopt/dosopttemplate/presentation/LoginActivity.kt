package org.sopt.dosopttemplate.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelableData
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortToastMessage

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private var user: User? = null

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                user = it.data?.getParcelableData(EXTRA_USER, User::class.java)
                saveSharedFile(user)
            }
        }

    private fun saveSharedFile(user: User?) {
        with(getSharedPreferenceUser().edit()) {
            user?.let {
                putString(ID, it.id)
                putString(PWD, it.password)
                putString(NICKNAME, it.nickname)
                putString(MBTI, it.mbti.toString())
            }
            apply()
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
        getSharedPreferenceUser().run {
            if (getBoolean(AUTO_LOGIN, false)) {
                successLogin()
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
            checkLoginInfo(binding.soptEvId.getEditText(), binding.soptEvPwd.getEditText())
        }
    }

    private fun initSignUp() {
        binding.btSignUp.setOnClickListener {
            goToSignUpActivity()
        }
    }

    private fun checkLoginInfo(id: String, pwd: String) {
        with(getSharedPreferenceUser()) {
            if (id == getString(ID, "") && pwd == getString(PWD, "")) {
                successLogin()
            } else {
                failLogin()
            }
        }
    }

    private fun successLogin() {
        getSharedPreferenceUser().edit().putBoolean(AUTO_LOGIN, true).apply()
        showShortToastMessage(getString(R.string.success_login))
        goToMainActivity()
    }

    private fun failLogin() {
        showShortToastMessage(getString(R.string.fail_login))
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityForResult.launch(intent)
        finish()
    }

    private fun goToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult.launch(intent)
    }

    companion object {
        const val EXTRA_USER = "user"
        const val ID = "ID"
        const val PWD = "PWD"
        const val NICKNAME = "NICKNAME"
        const val MBTI = "MBTI"
        const val AUTO_LOGIN = "AUTO_LOGIN"

        fun Context.getSharedPreferenceUser(): SharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    }
}