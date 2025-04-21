package com.xiaolu.basis.view.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xiaolu.basis.view.fragment.OneFragment
import com.xiaolu.basis.view.fragment.ThreeFragment
import com.xiaolu.basis.view.fragment.TwoFragment

/**
 * <p>
 * 首页Fragment对应的适配器
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/21 15:06:36
 */
@SuppressLint("UnsafeOptInUsageError")
class HomeFragmentAdapter : FragmentStateAdapter {

    private lateinit var fragmentManager: FragmentManager

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)

    constructor(fragment: Fragment) : super(fragment)

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager, lifecycle
    ) {
        this.fragmentManager = fragmentManager
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OneFragment.newInstance()
            1 -> TwoFragment.newInstance()
            2 -> ThreeFragment.newInstance()
            else -> Fragment()
        }
    }

    fun getItem(position: Int): Fragment? =
        fragmentManager.findFragmentByTag("f${getItemId(position)}")

    override fun getItemCount(): Int = 3
}