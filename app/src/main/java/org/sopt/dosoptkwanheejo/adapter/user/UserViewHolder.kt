package org.sopt.dosoptkwanheejo.adapter.user

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosoptkwanheejo.databinding.ItemUserBinding
import org.sopt.dosoptkwanheejo.model.dto.resp.user.UserDataResp
import org.sopt.dosoptkwanheejo.util.loadUrl

class UserViewHolder(
    private val binding: ItemUserBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UserDataResp) {
        binding.tvUserName.text = item.first_name
        binding.tvUserEmail.text = item.email
        binding.ivUserAvatar.loadUrl(binding.root, item.avatar, 200, 50)
    }
}