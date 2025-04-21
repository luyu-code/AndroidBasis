package com.xiaolu.basis.view.activity

import com.blankj.utilcode.util.ToastUtils
import com.xiaolu.basis.R
import com.xiaolu.basis.databinding.ActivityMainBinding
import com.xiaolu.basis.view.adapter.HomeFragmentAdapter

/**
 * 应用首页
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var pagerAdapter: HomeFragmentAdapter
    override fun initComponent() {
        pagerAdapter = HomeFragmentAdapter(this)
        binding.viewPager2.offscreenPageLimit = 1
        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.setCurrentItem(0, false)
        binding.rgTab.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_tab_mine_one -> {
                    binding.viewPager2.setCurrentItem(0, false)
                    binding.rbTabMineOne.isChecked = true
                }

                R.id.rb_tab_mine_two -> {
                    binding.viewPager2.setCurrentItem(1, false)
                    binding.rbTabMineTwo.isChecked = true
                }

                R.id.rb_tab_mine_three -> {
                    binding.viewPager2.setCurrentItem(2, false)
                    binding.rbTabMineThree.isChecked = true
                }
            }
        }
        binding.viewPager2.adapter = pagerAdapter
    }

    override fun loadData() {
    }
}