package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.adapter.HomeAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.Music
import org.sopt.dosopttemplate.model.Profile
import org.sopt.dosopttemplate.model.ProfileBirthday
import org.sopt.dosopttemplate.model.ProfileHeader

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private lateinit var homeAdapter: HomeAdapter
    private var testDeque = ArrayDeque<HomeProfileModel>()
    private var birthdayDeque = ArrayDeque<ProfileBirthday>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        initList()
        setBirthdayContent()
        homeAdapter.setProfileList(testDeque.toList())
    }

    private fun setBirthdayContent() {
        birthdayDeque.forEach {
            testDeque.add(1, it)
        }
    }

    private fun initList() {
        testDeque.apply {
            add(ProfileHeader("조관희", "졸리다", R.drawable.jokwanhee))
            add(
                Profile(
                    "SOPT YB 이상해씨",
                    "이상해씨!!!",
                    R.drawable.leaf1,
                    1697565421000,
                    1697565421000,
                    Music("야생화", "박효신")
                )
            )
            add(Profile("SOPT YB 이상해풀", "이상해풀!!!", R.drawable.leaf2, 0, 1696565421000, null))
            add(
                Profile(
                    "SOPT YB 이상해꽃 이름이 길어진다 길어진다 길어진다",
                    "이상해꽃 길어진다 길어진다 길어진다 길어진다 길어진다 길어진다",
                    R.drawable.leaf3,
                    0,
                    1697565421000,
                    Music("대장", "박효신")
                )
            )
            add(Profile("SOPT YB 파이리", "파이리", R.drawable.re1, 1697610124000, 1697565421000, null))
            add(Profile("SOPT YB 리자드", "리자드", R.drawable.re2, 0, 1697565421000, null))
            add(
                Profile(
                    "SOPT YB 리자몽",
                    "리자몽",
                    R.drawable.re3,
                    1697695757000,
                    1697695757000,
                    Music("나도", "박효신")
                )
            )
            add(
                Profile(
                    "SOPT YB 꼬부기",
                    "꼬부기",
                    R.drawable.turtle,
                    1697695757000,
                    1697695757000,
                    Music("몰라", "박효신")
                )
            )
            add(
                Profile(
                    "SOPT YB 어니부기",
                    "어니부기",
                    R.drawable.turtlemiddle,
                    1697565421000,
                    1697565421000,
                    null
                )
            )
            add(
                Profile(
                    "SOPT YB 거북왕",
                    "거북왕",
                    R.drawable.turtleking,
                    1697695757000,
                    1697695757000,
                    Music("노래", "박효신")
                )
            )
            add(
                Profile(
                    "SOPT YB 피카츄",
                    "피카츄",
                    R.drawable.pickchu,
                    1697565421000,
                    1697565421000,
                    null
                )
            )
            add(
                Profile(
                    "SOPT YB 라이츄",
                    "라이츄",
                    R.drawable.raichu,
                    1697565421000,
                    1697565421000,
                    Music("노래 제목이 길게하면 어떨까?", "박효신")
                )
            )
            add(
                Profile(
                    "이름이 길면 어떨까???? 어떨까???? 어떨까????",
                    "글씨가 길면 어떨까? 어떨까? 어떨까? 어떨까? 어떨까? 어떨까? 어떨까?",
                    null,
                    1697565421000,
                    1697565421000,
                    null
                )
            )
        }.forEach {
            if (it is Profile && it.checkBirthDay()) {
                birthdayDeque.apply {
                    add(ProfileBirthday(it.name, it.description, it.profileImage))
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