package com.xiaolu.basis.view.fragment

import androidx.fragment.app.Fragment
import com.xiaolu.basis.databinding.FragmentOneBinding

/**
 * <p>
 * 示例Fragment
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/21 15:00:19
 */
class OneFragment : BaseFragment<FragmentOneBinding>() {
    override fun initComponent() {

    }

    override fun loadData() {
    }

    companion object {
        fun newInstance() = OneFragment()
    }
}