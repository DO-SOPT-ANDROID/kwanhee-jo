package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.user.UserAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.model.dto.resp.user.UserResp
import org.sopt.dosopttemplate.presentation.home.dialog.HomeAddDialogFragment
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private lateinit var userAdapter: UserAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserAdapter()
        initAddButton()
        observeData()
    }

    private fun initUserAdapter() {
        userAdapter = UserAdapter()
        binding.rvHome.apply {
            adapter = userAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        userAdapter.submitList(homeViewModel.userList.value?.data)
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
        homeViewModel.userList.observe(viewLifecycleOwner) {
            if (it != UserResp()) {
                userAdapter.submitList(it.data)
            }
        }
    }

    override fun onDestroyView() {
        binding.rvHome.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}