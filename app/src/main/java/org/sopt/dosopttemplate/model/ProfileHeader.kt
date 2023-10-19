package org.sopt.dosopttemplate.model

data class ProfileHeader (
    val name: String,
    val description: String?,
    val profileImage: Int?
): HomeProfileModel()