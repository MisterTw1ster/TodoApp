package com.example.feature_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_views.databinding.ItemIconWithTextBinding

class IconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val binding: ItemIconWithTextBinding = ItemIconWithTextBinding
        .inflate(LayoutInflater.from(context), this)

    init {
        initAttrs(attrs, defStyleAttr)
    }

    fun setText(text: String?) {
       binding.tvFilterText.text = text
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ItemFilterView, defStyleAttr, 0)
        val text = typedArray.getString(R.styleable.ItemFilterView_text)
        val src =
            typedArray.getResourceId(R.styleable.ItemFilterView_src, R.drawable.minus_line_divider)

        setText(text)
        binding.ivFilterIcon.setImageResource(src)

        typedArray.recycle()
    }
}