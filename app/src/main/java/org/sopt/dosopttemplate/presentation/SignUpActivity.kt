package org.sopt.dosopttemplate.presentation

import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.MBTI
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.showShortSnackBar
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

            if (checkLength(user.id, 6, 10) && checkLength(
                    user.password,
                    8,
                    12
                ) && isSuccessNickname(user.nickname) && isSuccessMBTI(
                    user.mbti
                )
            ) {
                binding.root.showShortSnackBar(getString(R.string.success_sign_up))
                intent.putExtra("user", user)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                binding.root.showShortSnackBar(getString(R.string.fail_sign_up))
            }
        }
    }

    private fun checkLength(contents: String, min: Int, max: Int): Boolean =
        contents.length in min..max

    private fun isSuccessNickname(nickname: String): Boolean =
        nickname.isNotEmpty()

    private fun isSuccessMBTI(mbti: MBTI): Boolean =
        mbti != MBTI.ERROR

}