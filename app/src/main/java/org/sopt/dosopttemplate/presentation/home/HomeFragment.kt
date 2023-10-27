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
import org.sopt.dosopttemplate.util.sampleDeque

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private lateinit var homeAdapter: HomeAdapter
    private val viewModel: HomeViewModel by activityViewModels()
    private var birthdayDeque = ArrayDeque<HomeProfileModel.ProfileBirthday>()
    private var homeSampleDeque = sampleDeque.toMutableList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        initList()
        setBirthdayContent()
        setHomeProfileList()
        observeData()
    }

    private fun observeData() {
        viewModel.bottomItemId.observe(viewLifecycleOwner) {
            if (it == HomeBottomItem.HOME) {
                binding.rvHome.scrollToPosition(0)
            }
        }
    }

    private fun setHomeProfileList() {
        homeAdapter.submitList(homeSampleDeque.toList())
    }

    private fun setBirthdayContent() {
        birthdayDeque.forEach {
            homeSampleDeque.add(1, it)
        }
    }

    private fun initList() {
        if (birthdayDeque.isEmpty()) {
            homeSampleDeque.forEach {
                if (it is HomeProfileModel.Profile && it.checkBirthDay()) {
                    birthdayDeque.apply {
                        add(
                            HomeProfileModel.ProfileBirthday(
                                it.name,
                                it.description,
                                it.profileImage,
                                it.update
                            )
                        )
                    }
                }
            }
        }
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}