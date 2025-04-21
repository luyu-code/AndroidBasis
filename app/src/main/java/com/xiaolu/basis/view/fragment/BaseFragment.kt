package com.xiaolu.basis.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.FragmentBinding
import com.dylanc.viewbinding.base.FragmentBindingDelegate

/**
 * <p>
 * 基类Fragment
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 17:14:36
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment(),
    FragmentBinding<VB> by FragmentBindingDelegate() {

    /** 当前是否加载过 */
    private var loading: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loading = false
        return createViewWithBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        if (!loading) {
            loading = true
            onResumeInitData()
            return
        }
        onResumeLoadData()
    }

    /**
     * 在onResume中初始化数据
     */
    protected open fun onResumeInitData() {}

    /**
     * 在onResume中加载数据
     */
    protected open fun onResumeLoadData() {}

    /**
     * 初始化界面控件
     */
    abstract fun initComponent()

    /**
     * 加载数据
     *
     */
    abstract fun loadData()

    /**
     * 这个Fragment是否已经加载过
     */
    open fun isLoading(): Boolean {
        return loading
    }
}