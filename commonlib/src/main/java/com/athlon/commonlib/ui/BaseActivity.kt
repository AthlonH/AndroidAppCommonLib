package com.athlon.commonlib.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.athlon.commonlib.LayoutId
import java.lang.reflect.Method

/**
 * Athlon huang创建于2020-8-19
 * Kotlin基础Activity
 */
abstract class BaseActivity: AppCompatActivity() {

    var activityLayoutId: Int = 0
        set(value) {
            field = value
            setContentView(value)
            afterViewInit()
            initViewModels()
            viewModelsCreated()
        }

    open var viewModelProvider: ViewModelProvider?= null

    abstract fun afterViewInit()

    abstract fun initViewModels()

    abstract fun viewModelsCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelProvider = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
        val layoutIdAnno = this::class.java.getAnnotation(LayoutId::class.java)
        if (layoutIdAnno != null) {
            activityLayoutId = layoutIdAnno.id
        }

    }
}


