package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileContentsBinding
import org.sopt.dosopttemplate.model.ProfileBirthday
import org.sopt.dosopttemplate.util.roundedCornerGlide

class HomeContentsViewHolder(val binding: ItemProfileContentsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProfileBirthday) {
        binding.tvUserName.text = item.name
        binding.tvUserDescription.text = item.description

        if (item.profileImage == null) {
            binding.ivUser.roundedCornerGlide(binding.root, R.drawable.kakao_profile, 150, 50)
        } else {
            binding.ivUser.roundedCornerGlide(binding.root, item.profileImage, 150, 50)
        }
    }
}