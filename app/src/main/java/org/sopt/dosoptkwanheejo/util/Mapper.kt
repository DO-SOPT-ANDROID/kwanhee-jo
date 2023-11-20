package org.sopt.dosoptkwanheejo.util

fun String.toMBTI(): MBTI {
    return try {
        if (this.isEmpty()) MBTI.ERROR else MBTI.valueOf(this.uppercase())
    } catch (e: IllegalArgumentException) {
        return MBTI.ERROR
    }
}

//fun HomeProfileModel.Profile.toProfileBirth(): HomeProfileModel.ProfileBirthday =
//    HomeProfileModel.ProfileBirthday(
//        id = this.id,
//        name = this.name,
//        description = this.description,
//        profileImage = this.profileImage,
//        update = this.update,
//        music = this.music
//    )
