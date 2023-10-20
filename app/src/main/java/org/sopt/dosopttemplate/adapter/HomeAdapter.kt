package org.sopt.dosopttemplate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.databinding.ItemProfileContentsBinding
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.HomeViewTypeModel
import org.sopt.dosopttemplate.model.Profile
import org.sopt.dosopttemplate.model.ProfileBirthday
import org.sopt.dosopttemplate.model.ProfileHeader

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var profileList = emptyList<HomeProfileModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeViewTypeModel.Header.type -> HomeHeaderViewHolder(
                ItemProfileHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            HomeViewTypeModel.Birthday.type -> HomeContentsViewHolder(
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
            HomeViewTypeModel.Header.type ->
                (holder as HomeHeaderViewHolder).bind(profileList[position] as ProfileHeader)

            HomeViewTypeModel.Birthday.type ->
                (holder as HomeContentsViewHolder).bind(profileList[position] as ProfileBirthday)

            HomeViewTypeModel.Profile.type ->
                (holder as HomeViewHolder).bind(profileList[position] as Profile)
        }
    }

    override fun getItemCount(): Int = profileList.size

    override fun getItemViewType(position: Int): Int {
        return when (profileList[position]) {
            is ProfileHeader -> HomeViewTypeModel.Header.type
            is ProfileBirthday -> HomeViewTypeModel.Birthday.type
            is Profile -> HomeViewTypeModel.Profile.type
        }
    }

    fun setProfileList(profileList: List<HomeProfileModel>) {
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }
}