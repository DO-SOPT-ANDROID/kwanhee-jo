package org.sopt.dosopttemplate.model


data class Music(
    val title: String,
    val artist: String
) {
    fun musicAlbumTitle(): String = "$title - $artist"
}
