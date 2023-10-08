package org.sopt.dosopttemplate.util

import java.lang.IllegalArgumentException

fun String.toMBTI(): MBTI {
    return try {
        if (this.isEmpty()) MBTI.ERROR else MBTI.valueOf(this)
    } catch (e: IllegalArgumentException) {
        return MBTI.ERROR
    }
}