package com.example.feature_views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_views.databinding.ViewIconWithTextBelowBinding

class IconWithTextBelow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val binding: ViewIconWithTextBelowBinding = ViewIconWithTextBelowBinding
        .inflate(LayoutInflater.from(context), this)

    init {
        initAttrs(attrs, defStyleAttr)
    }

    fun setText(text: String?) {
       binding.tvText.text = text
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.IconWithTextBelow, defStyleAttr, 0)
        val text = typedArray.getString(R.styleable.IconWithTextBelow_textITB)
        val src =
            typedArray.getResourceId(R.styleable.IconWithTextBelow_srcITB, R.drawable.minus_line_divider)
        val srcColor = typedArray.getColor(R.styleable.IconWithTextBelow_srcColorITB, Color.WHITE)

        setText(text)
        binding.ivIcon.setImageResource(src)
        binding.ivIcon.setColorFilter(srcColor)

        typedArray.recycle()
    }
}