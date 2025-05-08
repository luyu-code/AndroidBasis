package com.xiaolu.basis.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import com.blankj.utilcode.util.ScreenUtils
import com.xiaolu.basis.databinding.DialogGeneralBinding

/**
 * <p>
 * 通用弹窗
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/5/8 14:01:37
 */
class GeneralDialog(context: Context) : AppCompatDialog(context) {
    private val binding: DialogGeneralBinding by lazy {
        DialogGeneralBinding.inflate(layoutInflater)
    }
    private var leftOnClickListener: (view: View) -> Unit = { _ -> }
    private var rightOnClickListener: (view: View) -> Unit = { _ -> }
    private var title: String = ""
    private var content: String = ""
    private var leftText: String = ""
    private var rightText: String = ""

    fun setLeftOnClickListener(listener: (view: View) -> Unit) {
        leftOnClickListener = listener
    }

    fun setRightOnClickListener(listener: (view: View) -> Unit) {
        rightOnClickListener = listener
    }

    fun setTitle(title: String) {
        this.title = title
    }
    fun setContent(content: String) {
        this.content = content
    }
    fun setLeftText(leftText: String) {
        this.leftText = leftText
    }

    fun setRightText(rightText: String) {
        this.rightText = rightText
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvTitle.text = title
        binding.tvContent.text = content
        binding.tvRight.text = rightText
        binding.tvLeft.text = leftText
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.apply {
            // 设置窗口属性
            val lp = attributes.apply {
                width =
                    (ScreenUtils.getScreenWidth() * 0.7).toInt()
                height = WindowManager.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER
            }
            attributes = lp
            setBackgroundDrawableResource(android.R.color.transparent)
        }
        binding.tvLeft.setOnClickListener {
            leftOnClickListener(it)
            dismiss()
        }
        binding.tvRight.setOnClickListener {
            rightOnClickListener(it)
            dismiss()
        }
    }
}