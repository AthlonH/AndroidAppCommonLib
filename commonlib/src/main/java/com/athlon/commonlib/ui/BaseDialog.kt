package com.athlon.commonlib.ui

import android.content.res.XmlResourceParser
import android.os.Bundle
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.athlon.commonlib.LayoutId
import kotlinx.android.extensions.LayoutContainer

abstract class BaseDialog: DialogFragment() {

    lateinit var viewModelProvider: ViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelProvider = ViewModelProvider(this.activity!!.viewModelStore, ViewModelProvider.NewInstanceFactory())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layoutIdAnno = this::class.java.getAnnotation(LayoutId::class.java)
        var rootView: View ?= null
        layoutIdAnno?.id?.let {
            rootView = layoutInflater.inflate(it, container, false)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        afterViewInit()
    }

    abstract fun afterViewInit()
}