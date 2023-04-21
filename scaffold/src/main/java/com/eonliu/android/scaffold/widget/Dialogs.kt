package com.eonliu.android.scaffold.widget

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.eonliu.android.scaffold.R
import com.eonliu.android.scaffold.databinding.DialogFragmentBinding
import com.eonliu.android.scaffold.util.setOnSingleClickListener

/**
 * 对话框
 *
 * @author Eon Liu
 */
class Dialogs : DialogFragment() {

    companion object {
        @JvmStatic
        fun newInstance(builder: Builder, host: Any): Dialogs {
            val dialogs = Dialogs()
            when (host) {
                is AppCompatActivity -> dialogs.fm = host.supportFragmentManager
                is Fragment -> dialogs.fm = host.childFragmentManager
                is FragmentManager -> dialogs.fm = host
                else -> throw IllegalArgumentException("host must be AppCompatActivity、Fragment or FragmentManager")
            }

            dialogs.builder = builder
            dialogs.isCancelable = false
            return dialogs
        }
    }

    private var _binding: DialogFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var builder: Builder
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        builder.titleText.isNotEmpty().let {
            binding.title.text = builder.titleText
            if (it) {
                binding.title.visibility = View.VISIBLE
            } else {
                binding.title.visibility = View.GONE
            }
        }
        binding.content.text = builder.contentText
        if (builder.contentSpannableString != null) {
            binding.content.movementMethod = LinkMovementMethod.getInstance()
            binding.content.text = builder.contentSpannableString
            binding.content.highlightColor = Color.TRANSPARENT
        }
        binding.negative.text = builder.negativeButtonText
        binding.positive.text = builder.positiveButtonText
        binding.neutral.text = builder.singleButtonText
        binding.content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, builder.textSize)
        binding.positive.setTextSize(TypedValue.COMPLEX_UNIT_DIP, builder.positiveButtonTextSize)
        binding.negative.setTextSize(TypedValue.COMPLEX_UNIT_DIP, builder.negativeButtonTextSize)
        if (builder.singleButton) {
            if (builder.isNewStyle) {
                binding.singleButton.visibility = View.VISIBLE
                binding.contentDivider.visibility = View.GONE
            } else {
                binding.neutral.visibility = View.VISIBLE
            }
            binding.doubleGroup.visibility = View.GONE
        } else {
            binding.neutral.visibility = View.GONE
            binding.doubleGroup.visibility = View.VISIBLE
        }
        binding.content.gravity = builder.contentTextGravity
        binding.negative.setOnSingleClickListener { builder.negativeClickListener?.invoke(this@Dialogs) }
        binding.positive.setOnSingleClickListener { builder.positiveClickListener?.invoke(this@Dialogs) }
        binding.neutral.setOnSingleClickListener { builder.singleClickListener?.invoke(this@Dialogs) }
        binding.singleButton.setOnSingleClickListener { builder.singleClickListener?.invoke(this@Dialogs) }
    }

    /**
     * 显示dialog
     */
    fun show(): Dialogs {
        show(fm, "")
        return this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isCancelable(isCancelable: Boolean): Dialogs {
        this.isCancelable = isCancelable
        return this
    }


    interface OnClickListener {
        fun onClick(v: Dialogs)
    }

    class Builder {
        var titleText = ""
        var contentText = ""
        var contentTextGravity = Gravity.CENTER
        var contentSpannableString: SpannableStringBuilder? = null
        var negativeButtonText = ""
        var positiveButtonText = ""
        var singleButtonText = ""
        var singleButton = false
        var isNewStyle = false
        var negativeClickListener: ((Dialogs) -> Unit)? = null
        var positiveClickListener: ((Dialogs) -> Unit)? = null
        var singleClickListener: ((Dialogs) -> Unit)? = null
        var textSize = 16f
        var positiveButtonTextSize = 16f
        var negativeButtonTextSize = 16f

        /**
         * 设置对话框标题
         */
        fun titleText(message: String): Builder {
            titleText = message
            return this
        }

        /**
         * 设置对话框文本内容对齐方式
         */
        fun contentTextGravity(gravity: Int): Builder {
            contentTextGravity = gravity
            return this
        }

        /**
         * 设置对话框文本内容
         */
        fun contentText(message: String): Builder {
            contentText = message
            return this
        }

        /**
         * 设置对话框文本内容
         */
        fun contentText(ss: SpannableStringBuilder): Builder {

            contentSpannableString = ss
            return this
        }

        /**
         * 设置左侧（消极的）button文本
         */
        fun negativeButtonText(text: String): Builder {
            negativeButtonText = text
            return this
        }

        /**
         * 设置右侧（积极的）button文本
         */
        fun positiveButtonText(text: String): Builder {
            positiveButtonText = text
            return this
        }

        /**
         * 设置单个（中立的）button文本
         */
        fun singleButtonText(text: String): Builder {
            singleButtonText = text
            return this
        }

        /**
         * 是否展示单个button
         */
        fun singleButton(single: Boolean, isNewStyle: Boolean = false): Builder {
            singleButton = single
            this.isNewStyle = isNewStyle
            return this
        }

        /**
         * 设置左侧（消极的）button点击事件
         */
        fun setNegativeClickListener(listener: (Dialogs) -> Unit): Builder {
            negativeClickListener = listener
            return this
        }

        /**
         * 设置右侧（积极的）button点击事件
         */
        fun setPositiveClickListener(listener: Dialogs.() -> Unit): Builder {
            positiveClickListener = listener
            return this
        }

        /**
         * 设置单个（中立的）button点击事件
         */
        fun setSingleClickListener(listener: (Dialogs) -> Unit): Builder {
            singleClickListener = listener
            return this
        }

        /**
         * 设置中间内容的字体大小
         * */
        fun setContentTextSize(textSizeSp: Float): Builder {
            textSize = textSizeSp
            return this
        }

        /**
         * 设置中间内容的对齐方式
         * */
        fun setContentTextGravity(gravity: Int): Builder {
            contentTextGravity = gravity
            return this
        }

        /**
         * 确定按钮字体大小
         * */
        fun setPositiveTextSize(textSizeSp: Float): Builder {
            positiveButtonTextSize = textSizeSp
            return this
        }

        /**
         * 取消按钮字体大小
         * */
        fun setNegativeTextSize(textSizeSp: Float): Builder {
            negativeButtonTextSize = textSizeSp
            return this
        }

        fun create(): Builder {
            return this
        }
    }
}