package org.sopt.dosoptkwanheejo.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.dosoptkwanheejo.R
import org.sopt.dosoptkwanheejo.base.BaseFragment
import org.sopt.dosoptkwanheejo.databinding.FragmentAndroidBinding
import org.sopt.dosoptkwanheejo.presentation.home.viewmodel.HomeViewModel

class AndroidFragment : BaseFragment<FragmentAndroidBinding>() {
    private val homeViewModel: HomeViewModel by activityViewModels()

    override val layoutId: Int
        get() = R.layout.fragment_android

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTranslate()
        observeData()
    }

    private fun onTranslate() {
        binding.btnTranslate.setOnClickListener {
            if (binding.etTranslateText.text.isNotEmpty()) {
                homeViewModel.getTranslatedTextToPapago(
                    "en",
                    "ko",
                    binding.etTranslateText.text.toString()
                )
            }
        }
    }

    private fun observeData() {
        homeViewModel.translatedText.observe(viewLifecycleOwner) {
            binding.tvBeTranslatedText.text = it
        }
    }

    companion object {
        fun newInstance(): AndroidFragment {
            return AndroidFragment()
        }
    }
}