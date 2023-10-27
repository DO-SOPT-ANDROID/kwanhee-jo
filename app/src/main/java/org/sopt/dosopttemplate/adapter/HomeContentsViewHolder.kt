package org.sopt.dosopttemplate.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileContentsBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeContentsViewHolder(val binding: ItemProfileContentsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HomeProfileModel.ProfileBirthday) {
        binding.tvUserName.text = item.name
        binding.tvUserDescription.text = item.description
        binding.ivUpdateDot.isVisible = item.currentUpdateProfile()

        if (item.profileImage == null) {
            binding.ivUser.roundedCornerGlide(binding.root, R.drawable.kakao_profile, 150, 50)
        } else {
            binding.ivUser.roundedCornerGlide(binding.root, item.profileImage, 150, 50)
        }
    }
}