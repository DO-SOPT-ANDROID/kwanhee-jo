package org.sopt.dosoptkwanheejo.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class RespResult(
    @SerialName("message")
    val message: String = ""
)
