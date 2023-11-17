package org.sopt.dosoptkwanheejo.api

import org.sopt.dosoptkwanheejo.model.dto.resp.NaverPapagoResp
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NaverAPI {
    @FormUrlEncoded
    @POST("v1/papago/n2mt")
    fun getTranslatedTextToPapago(
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ): Call<NaverPapagoResp>
}