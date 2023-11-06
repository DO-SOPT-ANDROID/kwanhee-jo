package org.sopt.dosopttemplate.adapter.land

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileContentsLandBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeLandContentsViewHolder(val binding: ItemProfileContentsLandBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HomeProfileModel.ProfileBirthday) {
        binding.tvProfileName.text = item.name
        binding.tvProfileDescription.text = item.description
        if (item.profileImage == null) {
            binding.ivProfile.roundedCornerGlide(binding.root, R.drawable.kakao_profile, 400, 50)
        } else {
            binding.ivProfile.roundedCornerGlide(binding.root, item.profileImage, 400, 50)
        }
    }
}