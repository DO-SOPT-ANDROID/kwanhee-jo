package org.sopt.dosopttemplate.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelableData
import org.sopt.dosopttemplate.util.hideKeyboard

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private var user: User? = null

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                user = it.data?.getParcelableData(EXTRA_USER, User::class.java)
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
        with(getPreferences(MODE_PRIVATE)) {
            getString(ID, "")?.let { id ->
                getString(PWD, "")?.let { pwd ->
                    if (id.isNotEmpty() && pwd.isNotEmpty()) {
                        successLogin()
                    }
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
            checkLoginInfo(binding.soptEvId.getEditText(), binding.soptEvPwd.getEditText())
        }
    }

    private fun initSignUp() {
        binding.btSignUp.setOnClickListener {
            goToSignUpActivity()
        }
    }

    private fun checkLoginInfo(id: String, pwd: String) {
        if (id == user?.id && pwd == user?.password) {
            saveSharedFile(id, pwd)
            successLogin()
        } else {
            failLogin()
        }
    }

    private fun saveSharedFile(id: String, pwd: String) {
        with (getPreferences(Context.MODE_PRIVATE).edit()) {
            putString(ID, id)
            putString(PWD, pwd)
            apply()
        }
    }
    private fun successLogin() {
        Toast.makeText(this, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
        goToMainActivity()
    }

    private fun failLogin() {
        Toast.makeText(this, getString(R.string.fail_login), Toast.LENGTH_SHORT).show()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_USER, user)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
        startActivityForResult.launch(intent)
    }

    private fun goToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult.launch(intent)
    }


    companion object {
        const val EXTRA_USER = "user"
        const val ID = "ID"
        const val PWD = "PWD"
    }
}