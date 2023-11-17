package org.sopt.dosoptkwanheejo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.dosoptkwanheejo.util.MBTI

@Parcelize
data class User(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: MBTI
): Parcelable