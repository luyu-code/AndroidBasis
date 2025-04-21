package com.xiaolu.basis.view.fragment

import com.xiaolu.basis.databinding.FragmentTwoBinding

/**
 * <p>
 *  示例Fragment
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/21 15:00:28
 */
class TwoFragment : BaseFragment<FragmentTwoBinding>() {
    override fun initComponent() {

    }

    override fun loadData() {
    }

    companion object {
        fun newInstance() = TwoFragment()
    }
}