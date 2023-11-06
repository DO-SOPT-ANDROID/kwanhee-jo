package org.sopt.dosopttemplate.presentation.home

import android.content.res.Configuration
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel
import org.sopt.dosopttemplate.util.sampleDeque
import org.sopt.dosopttemplate.util.showShortToastMessage
import org.sopt.dosopttemplate.util.toProfileBirth

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    private var backPressedTime = 0L
    private val homeViewModel: HomeViewModel by viewModels()
    private var birthdayDeque = ArrayDeque<HomeProfileModel.ProfileBirthday>()
    private var homeSampleDeque = sampleDeque.toMutableList()

    override fun initView() {
        initList()
        setBirthdayContent()
        setHomeProfileList()
        initFragment()
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            changeOrientation(HomeFragment.newInstance(), HomeLandFragment.newInstance())
            binding.bottomNavigationView.selectedItemId = R.id.home
            homeViewModel.setBottomItemId(HomeBottomItem.HOME)
        }
    }

    private fun initList() {
        if (birthdayDeque.isEmpty()) {
            homeSampleDeque.forEach {
                if (it is HomeProfileModel.Profile && it.checkBirthDay()) {
                    birthdayDeque.add(it.toProfileBirth())
                }
            }
        }
    }

    private fun setBirthdayContent() {
        birthdayDeque.forEach {
            homeSampleDeque.add(1, it)
        }
    }

    private fun setHomeProfileList() {
        homeViewModel.setProfileList(homeSampleDeque.toList())
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (binding.bottomNavigationView.selectedItemId == R.id.home) {
            changeOrientation(HomeFragment.newInstance(), HomeLandFragment.newInstance())
        }
    }

    override fun initEvent() {
        initBottomNavigationClick()
        initBackPressed()
        initBottomNavigationReSelected()
    }

    private fun initBottomNavigationReSelected() {
        binding.bottomNavigationView.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.home -> homeViewModel.setBottomItemId(HomeBottomItem.HOME)
                R.id.android -> homeViewModel.setBottomItemId(HomeBottomItem.ANDROID)
                R.id.my_page -> homeViewModel.setBottomItemId(HomeBottomItem.MYPAGE)
            }
        }
    }

    private fun initBottomNavigationClick() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    changeOrientation(HomeFragment.newInstance(), HomeLandFragment.newInstance())
                    true
                }

                R.id.android -> {
                    replaceFragment(AndroidFragment.newInstance())
                    true
                }

                R.id.my_page -> {
                    replaceFragment(MyPageFragment.newInstance())
                    true
                }

                else -> false
            }
        }
    }

    private fun changeOrientation(portraitFragment: Fragment, landscapeFragment: Fragment) {
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> replaceFragment(portraitFragment)
            Configuration.ORIENTATION_LANDSCAPE -> replaceFragment(landscapeFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun initBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime >= 2000L) {
                    backPressedTime = System.currentTimeMillis()
                    this@HomeActivity.showShortToastMessage(getString(R.string.finish_dialog))
                } else {
                    finish()
                }
            }
        })
    }
}