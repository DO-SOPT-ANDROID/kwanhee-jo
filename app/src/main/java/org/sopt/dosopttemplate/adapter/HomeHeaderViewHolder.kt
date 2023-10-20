package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeHeaderViewHolder(val binding: ItemProfileHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HomeProfileModel.ProfileHeader) {
        binding.tvUserName.text = item.name
        binding.tvUserDescription.text = item.description

        if (item.profileImage == null) {
            binding.ivUser.roundedCornerGlide(binding.root, R.drawable.kakao_profile, 200, 50)
        } else {
            binding.ivUser.roundedCornerGlide(binding.root, item.profileImage, 200, 50)
        }
    }
}