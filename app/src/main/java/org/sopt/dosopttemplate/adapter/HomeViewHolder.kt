package org.sopt.dosopttemplate.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeViewHolder(
    private val binding: ItemProfileBinding,
    private val onClick: (HomeProfileModel) -> Unit,
    private val onLongClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HomeProfileModel.Profile) {
        binding.tvUserName.text = item.name
        binding.tvUserDescription.text = item.description
        initProfileImage(item)
        initUpdateStatus(item)
        initMusicStatus(item)
        binding.root.setOnClickListener {
            onClick(item)
        }
        binding.root.setOnLongClickListener {
            onLongClick(item.id)
            false
        }
    }

    private fun initMusicStatus(item: HomeProfileModel.Profile) {
        if (item.music != null) {
            binding.tvStatusMessage.text = item.music.musicAlbumTitle()
            binding.tvStatusMessage.visibility = View.VISIBLE
        } else {
            binding.tvStatusMessage.visibility = View.GONE
        }
    }

    private fun initProfileImage(item: HomeProfileModel.Profile) {
        if (item.profileImage == null) {
            binding.ivUser.roundedCornerGlide(binding.root, R.drawable.kakao_profile, 150, 50)
        } else {
            binding.ivUser.roundedCornerGlide(binding.root, item.profileImage, 150, 50)
        }
    }

    private fun initUpdateStatus(item: HomeProfileModel.Profile) {
        binding.ivUpdateDot.isVisible = item.currentUpdateProfile()
    }
}