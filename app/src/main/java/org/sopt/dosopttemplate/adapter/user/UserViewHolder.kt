package org.sopt.dosopttemplate.adapter.user

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemUserBinding
import org.sopt.dosopttemplate.model.dto.resp.user.UserDataResp
import org.sopt.dosopttemplate.util.loadUrl
import org.sopt.dosopttemplate.util.roundedCornerGlide

class UserViewHolder(
    private val binding: ItemUserBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UserDataResp) {
        binding.tvUserName.text = item.first_name
        binding.tvUserEmail.text = item.email
        binding.ivUserAvatar.loadUrl(binding.root, item.avatar, 200, 50)
    }
}