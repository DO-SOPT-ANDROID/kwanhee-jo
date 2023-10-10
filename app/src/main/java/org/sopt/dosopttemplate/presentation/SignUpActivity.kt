package org.sopt.dosopttemplate.presentation

import android.widget.Toast
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.MBTI
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.EXTRA_USER
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.toMBTI

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    override fun initView() {

    }

    override fun initEvent() {
        initDoSignUp()
    }

    private fun initDoSignUp() {
        binding.btSignUp.setOnClickListener {
            val user = User(
                binding.soptEvId.getEditText(),
                binding.soptEvPwd.getEditText(),
                binding.soptEvNickname.getEditText(),
                binding.soptEvMbti.getEditText().toMBTI()
            )

            if (checkLength(user.id, 6, 10) && checkLength(user.password, 8, 12) &&
                isSuccessNickname(user.nickname) && isSuccessMBTI(user.mbti)
            ) {
                successSignUp(user)
            } else {
                failSignUp()
            }
        }
    }

    private fun successSignUp(user: User) {
        binding.root.showShortSnackBar(getString(R.string.success_sign_up))
        intent.putExtra(EXTRA_USER, user)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun failSignUp() {
        binding.root.showShortSnackBar(getString(R.string.fail_sign_up))
    }

    private fun checkLength(contents: String, min: Int, max: Int): Boolean =
        contents.length in min..max

    private fun isSuccessNickname(nickname: String): Boolean =
        nickname.isNotEmpty()

    private fun isSuccessMBTI(mbti: MBTI): Boolean =
        mbti != MBTI.ERROR

}