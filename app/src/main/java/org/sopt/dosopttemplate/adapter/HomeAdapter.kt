package org.sopt.dosopttemplate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.databinding.ItemProfileContentsBinding
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.HomeViewTypeModel

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
                (holder as HomeHeaderViewHolder).bind(profileList[position] as HomeProfileModel.ProfileHeader)

            HomeViewTypeModel.Birthday.type ->
                (holder as HomeContentsViewHolder).bind(profileList[position] as HomeProfileModel.ProfileBirthday)

            HomeViewTypeModel.Profile.type ->
                (holder as HomeViewHolder).bind(profileList[position] as HomeProfileModel.Profile)
        }
    }

    override fun getItemCount(): Int = profileList.size

    override fun getItemViewType(position: Int): Int {
        return when (profileList[position]) {
            is HomeProfileModel.ProfileHeader -> HomeViewTypeModel.Header.type
            is HomeProfileModel.ProfileBirthday -> HomeViewTypeModel.Birthday.type
            is HomeProfileModel.Profile -> HomeViewTypeModel.Profile.type
        }
    }

    fun setProfileList(profileList: List<HomeProfileModel>) {
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }
}