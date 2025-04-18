package com.xiaolu.basis.view.activity

import com.blankj.utilcode.util.ToastUtils
import com.xiaolu.basis.databinding.ActivityMainBinding

/**
 * 应用首页
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initComponent() {
        binding.button.setOnClickListener {
            ToastUtils.showShort("点击了")
        }
    }

    override fun loadData() {
    }
}