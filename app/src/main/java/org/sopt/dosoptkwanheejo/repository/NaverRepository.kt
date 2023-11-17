package org.sopt.dosoptkwanheejo.repository

import org.sopt.dosoptkwanheejo.db.remote.NaverApiHelper
import org.sopt.dosoptkwanheejo.model.dto.resp.NaverPapagoResult

class NaverRepository(private val naverApiHelper: NaverApiHelper) {
    fun getTranslatedTextToPapago(source: String, target: String, text: String, onResponse: (NaverPapagoResult) -> Unit) {
        naverApiHelper.getTranslatedTextToPapago(source, target, text, onResponse)
    }
}