package org.sopt.dosopttemplate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.databinding.ItemProfileContentsBinding
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.Profile
import org.sopt.dosopttemplate.model.ProfileBirthday
import org.sopt.dosopttemplate.model.ProfileHeader

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var profileList = emptyList<HomeProfileModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HomeHeaderViewHolder(
                ItemProfileHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            VIEW_TYPE_BIRTHDAY -> HomeContentsViewHolder(
                ItemProfileContentsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            else -> HomeViewHolder(
                ItemProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HEADER ->
                (holder as HomeHeaderViewHolder).bind(profileList[position] as ProfileHeader)

            VIEW_TYPE_BIRTHDAY ->
                (holder as HomeContentsViewHolder).bind(profileList[position] as ProfileBirthday)

            VIEW_TYPE_PROFILE ->
                (holder as HomeViewHolder).bind(profileList[position] as Profile)
        }
    }

    override fun getItemCount(): Int = profileList.size

    override fun getItemViewType(position: Int): Int {
        return when (profileList[position]) {
            is ProfileHeader -> VIEW_TYPE_HEADER
            is ProfileBirthday -> VIEW_TYPE_BIRTHDAY
            is Profile -> VIEW_TYPE_PROFILE
        }
    }

    fun setProfileList(profileList: List<HomeProfileModel>) {
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }


    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_BIRTHDAY = 1
        private const val VIEW_TYPE_PROFILE = 2
    }
}