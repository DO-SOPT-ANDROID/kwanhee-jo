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

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private var user: User? = null

    private val resultActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getParcelableExtra("user", User::class.java)
                } else {
                    it.data?.getParcelableExtra("user")
                }
            }
        }

    override fun initView() {
        binding.soptEvPwd.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
    }

    override fun initEvent() {
        binding.btLogin.setOnClickListener {
            if (binding.soptEvId.getEditText() == user?.id && binding.soptEvPwd.getEditText() == user?.password) {
                Toast.makeText(this, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("user", user)
                }
                resultActivity.launch(intent)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.fail_login), Toast.LENGTH_SHORT).show()
            }
        }
        binding.btSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultActivity.launch(intent)
        }
    }
}