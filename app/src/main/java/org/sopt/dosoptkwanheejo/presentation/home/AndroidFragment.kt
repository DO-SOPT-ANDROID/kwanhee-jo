package org.sopt.dosoptkwanheejo.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.dosoptkwanheejo.R
import org.sopt.dosoptkwanheejo.base.BaseFragment
import org.sopt.dosoptkwanheejo.databinding.FragmentAndroidBinding

class AndroidFragment : BaseFragment<FragmentAndroidBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_android

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): AndroidFragment {
            return AndroidFragment()
        }
    }
}