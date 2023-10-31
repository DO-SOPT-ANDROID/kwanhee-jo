package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.HomeAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        observeData()
    }

    private fun observeData() {
        homeViewModel.bottomItemId.observe(viewLifecycleOwner) {
            if (it == HomeBottomItem.HOME) {
                binding.rvHome.scrollToPosition(0)
            }
        }
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(::onClick)
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
        }
        homeAdapter.submitList(homeViewModel.profileList.value)
    }

    private fun onClick(item: HomeProfileModel) {
        // handle click item
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}