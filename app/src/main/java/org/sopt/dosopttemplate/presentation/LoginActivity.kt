package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Build
import android.text.InputType
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelableData

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private var user: User? = null

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                user = it.data?.getParcelableData(EXTRA_USER, User::class.java)
            }
        }

    override fun initView() {
        binding.soptEvPwd.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
    }

    override fun initEvent() {
        initLogin()
        initSignUp()
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
            successLogin()
        } else {
            failLogin()
        }
    }

    private fun successLogin() {
        Toast.makeText(this, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
        goToMainActivity()
        finish()
    }

    private fun failLogin() {
        Toast.makeText(this, getString(R.string.fail_login), Toast.LENGTH_SHORT).show()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_USER, user)
        }
        startActivityForResult.launch(intent)
    }

    private fun goToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult.launch(intent)
    }


    companion object {
        const val EXTRA_USER = "user"
    }
}