package org.sopt.dosopttemplate.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.model.Profile
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeViewHolder(val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Profile) {
        binding.tvUserName.text = item.name
        binding.tvUserDescription.text = item.description
        initProfileImage(item)
        initUpdateStatus(item)
        initMusicStatus(item)
    }

    private fun initMusicStatus(item: Profile) {
        if (item.music != null) {
            binding.tvStatusMessage.text = item.music.musicAlbumTitle()
            binding.tvStatusMessage.visibility = View.VISIBLE
        } else {
            binding.tvStatusMessage.visibility = View.GONE
        }
    }

    private fun initProfileImage(item: Profile) {
        if (item.profileImage == null) {
            binding.ivUser.roundedCornerGlide(binding.root, R.drawable.kakao_profile, 150, 50)
        } else {
            binding.ivUser.roundedCornerGlide(binding.root, item.profileImage, 150, 50)
        }
    }

    private fun initUpdateStatus(item: Profile) {
        binding.ivUpdateDot.isVisible = item.currentUpdateProfile()
    }
}