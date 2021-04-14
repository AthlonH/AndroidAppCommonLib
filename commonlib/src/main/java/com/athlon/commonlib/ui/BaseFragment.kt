package com.athlon.commonlib.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.athlon.commonlib.LayoutId

abstract class BaseFragment: Fragment() {

    lateinit var viewModelProvider: ViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelProvider = ViewModelProvider(this.activity!!.viewModelStore, ViewModelProvider.NewInstanceFactory())
    }

    open var fragmentRootView: View ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutIdAnno = this::class.java.getAnnotation(LayoutId::class.java)
        if (layoutIdAnno != null) {
            fragmentRootView = inflater.inflate(layoutIdAnno.id, container, false)
        }
        return fragmentRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        afterViewInit()
        initViewModels()
        viewModelsCreated()
    }

    abstract fun afterViewInit()

    abstract fun initViewModels()

    abstract fun viewModelsCreated()
}