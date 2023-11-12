package org.sopt.dosopttemplate.presentation.home

import android.content.res.Configuration
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.DoSoptApp
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.presentation.home.viewmodel.HomeViewModel
import org.sopt.dosopttemplate.repository.UserRepository
import org.sopt.dosopttemplate.util.UserViewModelFactory
import org.sopt.dosopttemplate.util.showShortToastMessage

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    private var backPressedTime = 0L
    private lateinit var homeViewModel: HomeViewModel

    override fun initView() {
        initViewModel()
        initFragment()
        homeViewModel.getUserList()
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(UserRepository(DoSoptApp.getUserApiHelperInstance()))
        ).get(HomeViewModel::class.java)
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            replaceFragment(HomeFragment.newInstance())
            binding.bottomNavigationView.selectedItemId = R.id.home
            homeViewModel.setBottomItemId(HomeBottomItem.HOME)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
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
                    replaceFragment(HomeFragment.newInstance())
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

//    private fun changeOrientation(portraitFragment: Fragment, landscapeFragment: Fragment) {
//        when (resources.configuration.orientation) {
//            Configuration.ORIENTATION_PORTRAIT -> replaceFragment(portraitFragment)
//            Configuration.ORIENTATION_LANDSCAPE -> replaceFragment(landscapeFragment)
//        }
//    }

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