package org.sopt.dosopttemplate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemProfileBinding
import org.sopt.dosopttemplate.databinding.ItemProfileContentsBinding
import org.sopt.dosopttemplate.databinding.ItemProfileHeaderBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.HomeViewTypeModel

class HomeAdapter(private val onClick: (HomeProfileModel) -> Unit) :
    ListAdapter<HomeProfileModel, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeViewTypeModel.Header.type -> HomeHeaderViewHolder(
                binding = ItemProfileHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                onClick = onClick
            )

            HomeViewTypeModel.Birthday.type -> HomeContentsViewHolder(
                binding = ItemProfileContentsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                onClick = onClick
            )

            else -> HomeViewHolder(
                binding = ItemProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                onClick = onClick
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HomeViewTypeModel.Header.type ->
                (holder as HomeHeaderViewHolder).bind(currentList[position] as HomeProfileModel.ProfileHeader)

            HomeViewTypeModel.Birthday.type ->
                (holder as HomeContentsViewHolder).bind(currentList[position] as HomeProfileModel.ProfileBirthday)

            HomeViewTypeModel.Profile.type ->
                (holder as HomeViewHolder).bind(currentList[position] as HomeProfileModel.Profile)
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