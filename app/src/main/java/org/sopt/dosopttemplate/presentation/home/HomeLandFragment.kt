package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.land.HomeLandAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeLandBinding
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel

class HomeLandFragment : BaseFragment<FragmentHomeLandBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home_land
    private lateinit var homeLandAdapter: HomeLandAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLandAdapter()
        initViewPagerIndicator()
    }

    private fun initLandAdapter() {
        homeLandAdapter = HomeLandAdapter()
        binding.vpLandscapeContainer.adapter = homeLandAdapter
        homeLandAdapter.submitList(homeViewModel.profileList.value)
    }

    private fun initViewPagerIndicator() {
        TabLayoutMediator(
            binding.tabLayout,
            binding.vpLandscapeContainer
        ) { tab, position ->
            binding.vpLandscapeContainer.currentItem = tab.position
        }.attach()
    }

    companion object {
        fun newInstance(): HomeLandFragment = HomeLandFragment()
    }
}