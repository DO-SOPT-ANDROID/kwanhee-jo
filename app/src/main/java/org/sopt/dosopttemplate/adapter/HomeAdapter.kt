package org.sopt.dosopttemplate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.model.Profile

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var profileList = emptyList<Profile>()

    class ViewHolder(val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Profile) {
            binding.tvUserName.text = item.name
            binding.tvUserDescription.text = item.description
            initProfileImage(item)
            initUpdateStatus(item)
            initMusicStatus(item)

            if (item.checkBirthDay("yyyy-MM-dd")) {
                binding.ivStatus.visibility = View.VISIBLE
            }
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
                Glide.with(binding.root)
                    .load(R.drawable.kakao_profile)
                    .override(150)
                    .transform(MultiTransformation(CenterCrop(), RoundedCorners(50)))
                    .into(binding.ivUser)
            } else {
                Glide.with(binding.root)
                    .load(item.profileImage)
                    .override(150)
                    .transform(MultiTransformation(CenterCrop(), RoundedCorners(50)))
                    .into(binding.ivUser)
            }
        }

        private fun initUpdateStatus(item: Profile) {
            binding.ivUpdateDot.isVisible = item.currentUpdateProfile()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profileList[position])
    }

    override fun getItemCount(): Int = profileList.size

    fun setProfileList(profileList: List<Profile>) {
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }
}