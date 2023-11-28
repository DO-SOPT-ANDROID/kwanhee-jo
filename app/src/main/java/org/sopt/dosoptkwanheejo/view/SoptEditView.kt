package org.sopt.dosoptkwanheejo.view

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.sopt.dosoptkwanheejo.R
import org.sopt.dosoptkwanheejo.databinding.CustomEditViewBinding

class SoptEditView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private lateinit var binding: CustomEditViewBinding
    var title: String? = null
    var editHint: String? = null
    var titleSize: Float? = null

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
            titleSize = getDimension(
                R.styleable.SoptEditView_android_textSize,
                context.resources.getDimension(R.dimen.sp_14)
            )
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
        titleSize?.let {
            binding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, it)
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

    fun addTextChangedListener(textWatcher: TextWatcher) {
        binding.etContent.addTextChangedListener(textWatcher)
    }

    fun setBackgroundTint(color: Int) {
        binding.etContent.backgroundTintList =
            ContextCompat.getColorStateList(binding.root.context, color)
    }

    fun isVisibleError(visible: Boolean) {
        binding.ivError.isVisible = visible
    }
}
