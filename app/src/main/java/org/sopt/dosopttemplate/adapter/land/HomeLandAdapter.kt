package org.sopt.dosopttemplate.adapter.land

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemProfileContentsLandBinding
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderLandBinding
import org.sopt.dosopttemplate.databinding.ItemProfileLandBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.HomeViewTypeModel

class HomeLandAdapter : ListAdapter<HomeProfileModel, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeViewTypeModel.Header.type -> HomeLandHeaderViewHolder(
                ItemProfileHeaderLandBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            HomeViewTypeModel.Birthday.type -> HomeLandContentsViewHolder(
                ItemProfileContentsLandBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            else -> HomeLandViewHolder(
                ItemProfileLandBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HomeViewTypeModel.Header.type ->
                (holder as HomeLandHeaderViewHolder).bind(currentList[position] as HomeProfileModel.ProfileHeader)

            HomeViewTypeModel.Birthday.type ->
                (holder as HomeLandContentsViewHolder).bind(currentList[position] as HomeProfileModel.ProfileBirthday)

            HomeViewTypeModel.Profile.type ->
                (holder as HomeLandViewHolder).bind(currentList[position] as HomeProfileModel.Profile)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is HomeProfileModel.ProfileHeader -> HomeViewTypeModel.Header.type
            is HomeProfileModel.ProfileBirthday -> HomeViewTypeModel.Birthday.type
            is HomeProfileModel.Profile -> HomeViewTypeModel.Profile.type
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomeProfileModel>() {
            override fun areItemsTheSame(
                oldItem: HomeProfileModel,
                newItem: HomeProfileModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: HomeProfileModel,
                newItem: HomeProfileModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}