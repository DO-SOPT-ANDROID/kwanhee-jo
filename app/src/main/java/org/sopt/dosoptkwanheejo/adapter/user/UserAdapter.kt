package org.sopt.dosoptkwanheejo.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosoptkwanheejo.databinding.ItemUserBinding
import org.sopt.dosoptkwanheejo.model.dto.resp.user.UserDataResp

class UserAdapter : ListAdapter<UserDataResp, UserViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserDataResp>() {
            override fun areItemsTheSame(oldItem: UserDataResp, newItem: UserDataResp): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserDataResp, newItem: UserDataResp): Boolean {
                return oldItem == newItem
            }
        }
    }
}