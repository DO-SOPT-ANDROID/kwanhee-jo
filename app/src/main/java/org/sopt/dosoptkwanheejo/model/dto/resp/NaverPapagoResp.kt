package org.sopt.dosoptkwanheejo.model.dto.resp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NaverPapagoResp(
    @SerialName("message")
    val message: NaverPapagoType
)

@Serializable
data class NaverPapagoType(
    @SerialName("@type")
    val type: String,
    @SerialName("@service")
    val service: String,
    @SerialName("@version")
    val version: String,
    @SerialName("result")
    val result: NaverPapagoResult
)

@Serializable
data class NaverPapagoResult(
    @SerialName("srcLangType")
    val srcLangType: String,
    @SerialName("tarLangType")
    val tarLangType: String,
    @SerialName("translatedText")
    val translatedText: String,
    @SerialName("engineType")
    val engineType: String
)