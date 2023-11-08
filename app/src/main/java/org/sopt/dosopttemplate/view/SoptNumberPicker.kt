package org.sopt.dosopttemplate.view

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import androidx.constraintlayout.widget.ConstraintLayout
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.CustomNumberPickerBinding
import java.text.SimpleDateFormat

class SoptNumberPicker @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private lateinit var binding: CustomNumberPickerBinding

    init {
        attributeSet?.let { initAttr(it) }
        initView()
    }

    private fun initAttr(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SoptNumberPicker,
            0, 0
        ).apply { }
    }

    private fun initView() {
        binding =
            CustomNumberPickerBinding.bind(inflate(context, R.layout.custom_number_picker, this))

        // value 기본 값 설정 시 순서가 중요하다. minValue, maxValue 다음으로 호출되어야 한다
        binding.npYear.run {
            minValue = YEARS[0]
            maxValue = YEARS[YEARS.size - 1]
            value = SimpleDateFormat("yyyy").format(System.currentTimeMillis()).toInt() // 기본 값
            wrapSelectorWheel = true // false 시, minValue 와 maxValue 에서 멈추게 된다.
        }

        binding.npMonths.run {
            minValue = MONTHS[0]
            maxValue = MONTHS[MONTHS.size - 1]
            value = SimpleDateFormat("MM").format(System.currentTimeMillis()).toInt()
            wrapSelectorWheel = true
        }


        binding.npDay.run {
            minValue = DAYS[0]
            maxValue = DAYS[DAYS.size - 1]
            value = SimpleDateFormat("dd").format(System.currentTimeMillis()).toInt()
            wrapSelectorWheel = true
        }
    }

    fun getBirthday(): String =
        "${binding.npYear.value}-${binding.npMonths.value}-${binding.npDay.value} 00:00:00"

    companion object {
        private val currentYear =
            SimpleDateFormat("yyyy").format(System.currentTimeMillis()).toInt()
        val YEARS = (currentYear - 100..currentYear).toList()
        val MONTHS = (1..12).toList()
        val DAYS = (1..31).toList()
    }
}