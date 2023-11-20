package org.sopt.dosoptkwanheejo.db.remote

import org.sopt.dosoptkwanheejo.api.NaverAPI
import org.sopt.dosoptkwanheejo.model.dto.resp.NaverPapagoResp
import org.sopt.dosoptkwanheejo.model.dto.resp.NaverPapagoResult
import retrofit2.Call
import retrofit2.Response

class NaverApiHelper(private val naverAPI: NaverAPI) {
    fun getTranslatedTextToPapago(source: String, target: String, text: String, onResponse: (NaverPapagoResult) -> Unit) {
        naverAPI.getTranslatedTextToPapago(source, target, text).enqueue(object: retrofit2.Callback<NaverPapagoResp>{
            override fun onResponse(
                call: Call<NaverPapagoResp>,
                response: Response<NaverPapagoResp>
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.result?.let { onResponse(it) }
                }
            }

            override fun onFailure(call: Call<NaverPapagoResp>, t: Throwable) {
            }
        })
    }
}