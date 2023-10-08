package org.sopt.dosopttemplate.presentation

import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.MBTI
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.toMBTI

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    override fun initView() {

    }

    override fun initEvent() {
        binding.btSignUp.setOnClickListener {
            val user = User(
                binding.soptEvId.getEditText(),
                binding.soptEvPwd.getEditText(),
                binding.soptEvNickname.getEditText(),
                binding.soptEvMbti.getEditText().toMBTI()
            )

            if (isSuccessID(user.id) && isSuccessPassword(user.password) && isSuccessNickname(user.nickname) && isSuccessMBTI(
                    user.mbti
                )
            ) {
                showShortSnackBar(getString(R.string.success_sign_up))
                intent.putExtra("user", user)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                showShortSnackBar(getString(R.string.fail_sign_up))
            }
        }
    }

    private fun isSuccessID(id: String): Boolean =
        id.length in 6..10

    private fun isSuccessPassword(password: String): Boolean =
        password.length in 8..12

    private fun isSuccessNickname(nickname: String): Boolean =
        nickname.isNotEmpty()

    private fun isSuccessMBTI(mbti: MBTI): Boolean =
        mbti != MBTI.ERROR

}