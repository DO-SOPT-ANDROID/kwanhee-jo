package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.HomeAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.Music
import org.sopt.dosopttemplate.model.Profile

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private lateinit var homeAdapter: HomeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()

        // timestamp 찍는 사이트 : https://www.epochconverter.com/ (ms 로 가져오기)
        // 사진 url : https://picsum.photos/
        val testList = listOf<Profile>(
            Profile("SOPT YB 조관희", "hihi", "https://picsum.photos/200/300", 1697565421000, 1697565421000, Music("야생화", "박효신")),
            Profile("SOPT YB 조관희", "hihi", null, 0, 1696565421000, null),
            Profile("SOPT YB 조관희", "hihi", "https://picsum.photos/200/300", 0, 1697565421000, null),
            Profile("SOPT YB 조관희", "hihi", "https://picsum.photos/200", 0, 1697565421000, null),
            Profile("SOPT YB 조관희", "hihi", "https://picsum.photos/200", 0, 1697565421000, null),
        )
        homeAdapter.setProfileList(testList)
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