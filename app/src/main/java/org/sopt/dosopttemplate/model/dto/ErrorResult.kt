package org.sopt.dosopttemplate.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResult(
    @SerialName("message")
    val message: String = ""
)
