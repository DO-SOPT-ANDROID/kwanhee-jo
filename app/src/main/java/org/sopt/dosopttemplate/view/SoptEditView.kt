package org.sopt.dosopttemplate.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.CustomEditViewBinding

class SoptEditView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private lateinit var binding: CustomEditViewBinding
    var title: String? = null
    var editHint: String? = null

    init {
        attributeSet?.let {
            initAttr(it)
        }
        initView()
    }

    private fun initAttr(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SoptEditView,
            0, 0
        ).apply {
            title = getString(R.styleable.SoptEditView_title)
            editHint = getString(R.styleable.SoptEditView_editHint)
        }
    }

    private fun initView() {
        binding = CustomEditViewBinding.bind(inflate(context, R.layout.custom_edit_view, this))

        title?.let {
            binding.tvTitle.text = it
        }
        editHint?.let {
            binding.etContent.hint = it
        }
    }

    fun getEditText(): String {
        return binding.etContent.text.toString()
    }

    fun setEditHintText(text: String) {
        binding.etContent.setText(text)
    }

    fun setInputType(inputType: Int) {
        binding.etContent.inputType = inputType
    }
}
