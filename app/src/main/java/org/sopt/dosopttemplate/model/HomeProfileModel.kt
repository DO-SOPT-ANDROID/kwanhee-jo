package org.sopt.dosopttemplate.model

import java.text.SimpleDateFormat

sealed class HomeProfileModel {
    data class ProfileHeader(
        val id: Int,
        val name: String,
        val description: String?,
        val profileImage: Int?
    ) : HomeProfileModel()

    data class ProfileBirthday(
        val id: Int,
        val name: String,
        val description: String?,
        val profileImage: Int?,
        val update: Long,
        val music: Music?
    ) : HomeProfileModel() {
        fun currentUpdateProfile(): Boolean =
            System.currentTimeMillis() - update <= (1000 * 60 * 60 * 24)
    }

    data class Profile(
        val id: Int,
        val name: String,
        val description: String? = null,
        val profileImage: Int? = null,
        val birth: Long,
        val update: Long,
        val music: Music? = null
    ) : HomeProfileModel() {
        fun checkBirthDay(): Boolean = SimpleDateFormat("MM-dd").run {
            format(System.currentTimeMillis()) == format(birth)
        }

        fun currentUpdateProfile(): Boolean =
            System.currentTimeMillis() - update <= (1000 * 60 * 60 * 24)
    }
}