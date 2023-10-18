package org.sopt.dosopttemplate.model

import java.text.SimpleDateFormat

data class Profile(
    val name: String,
    val description: String?,
    val profileImage: String?,
    val birth: Long,
    val update: Long,
    val music: Music?
) {
    fun checkBirthDay(pattern: String): Boolean = SimpleDateFormat(pattern).run {
        format(System.currentTimeMillis()) == format(birth)
    }

    fun currentUpdateProfile(): Boolean =
        System.currentTimeMillis() - update <= (1000 * 60 * 60 * 24)
}
