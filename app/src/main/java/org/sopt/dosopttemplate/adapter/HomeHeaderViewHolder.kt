package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderBinding
import org.sopt.dosopttemplate.model.ProfileHeader

class HomeHeaderViewHolder(val binding: ItemProfileHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProfileHeader) {
        binding.tvUserName.text = item.name
        binding.tvUserDescription.text = item.description

        if (item.profileImage == null) {
            Glide.with(binding.root)
                .load(R.drawable.kakao_profile)
                .override(200)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(50)))
                .into(binding.ivUser)
        } else {
            Glide.with(binding.root)
                .load(item.profileImage)
                .override(200)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(50)))
                .into(binding.ivUser)
        }
    }
}