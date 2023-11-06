package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.HomeAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.presentation.detail.HomeDetailActivity
import org.sopt.dosopttemplate.presentation.home.dialog.HomeAddDialogFragment
import org.sopt.dosopttemplate.presentation.home.dialog.HomeRemoveDialogFragment
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        initAddButton()
        observeData()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(::onClick, ::onLongClick)
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
        }
        homeAdapter.submitList(homeViewModel.profileList.value)
    }

    private fun initAddButton() {
        binding.btnAdd.setOnClickListener {
            HomeAddDialogFragment().show(parentFragmentManager, null)
        }
    }

    private fun observeData() {
        homeViewModel.bottomItemId.observe(viewLifecycleOwner) {
            if (it == HomeBottomItem.HOME) {
                binding.rvHome.scrollToPosition(0)
            }
        }
        homeViewModel.profileList.observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }
    }

    private fun onClick(item: HomeProfileModel) {
        Intent(context, HomeDetailActivity::class.java).run {
            when (item) {
                is HomeProfileModel.ProfileHeader -> {
                    putExtra(PROFILE_NAME, item.name)
                    putExtra(PROFILE_DESCRIPTION, item.description)
                    putExtra(PROFILE_IMAGE, item.profileImage)
                }

                is HomeProfileModel.ProfileBirthday -> {
                    putExtra(PROFILE_NAME, item.name)
                    putExtra(PROFILE_DESCRIPTION, item.description)
                    putExtra(PROFILE_IMAGE, item.profileImage)
                    putExtra(PROFILE_BIRTH, true)
                    putExtra(PROFILE_MUSIC, item.music?.musicAlbumTitle() ?: "")
                }

                is HomeProfileModel.Profile -> {
                    putExtra(PROFILE_NAME, item.name)
                    putExtra(PROFILE_DESCRIPTION, item.description)
                    putExtra(PROFILE_IMAGE, item.profileImage)
                    putExtra(PROFILE_BIRTH, item.checkBirthDay())
                    putExtra(PROFILE_MUSIC, item.music?.musicAlbumTitle() ?: "")
                }
            }
        }.also { startActivity(it) }
    }

    private fun onLongClick(itemId: Int) {
        setFragmentResult(REQUEST_ITEM_ID_KEY, bundleOf(BUNDLE_ITEM_ID_KEY to itemId))
        HomeRemoveDialogFragment().show(parentFragmentManager, null)
    }

    override fun onDestroyView() {
        binding.rvHome.adapter = null
        super.onDestroyView()
    }

    companion object {
        const val PROFILE_NAME = "PROFILE_NAME"
        const val PROFILE_DESCRIPTION = "PROFILE_DESCRIPTION"
        const val PROFILE_IMAGE = "PROFILE_IMAGE"
        const val PROFILE_BIRTH = "PROFILE_BIRTH"
        const val PROFILE_MUSIC = "PROFILE_MUSIC"

        const val REQUEST_ITEM_ID_KEY = "REQUEST_ITEM_ID_KEY"
        const val BUNDLE_ITEM_ID_KEY = "BUNDLE_ITEM_ID_KEY"

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}