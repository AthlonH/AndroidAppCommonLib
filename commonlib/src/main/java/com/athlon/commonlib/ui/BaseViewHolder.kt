package com.athlon.commonlib.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athlon.commonlib.LayoutId
import com.athlon.commonlib.R
import kotlinx.android.extensions.LayoutContainer


abstract class BaseViewHolder<T>(container: ViewGroup):LayoutContainer {

    override val containerView: View?

    val viewHolderImpl: BaseViewHolderImpl

    init {
        var layoutIdAnno = this::class.java.getAnnotation(LayoutId::class.java)
        if (layoutIdAnno == null) {
            throw RuntimeException("ViewHolder layout id annotation is null")
        }
        var inflater = LayoutInflater.from(container.context)
        viewHolderImpl = BaseViewHolderImpl(inflater.inflate(layoutIdAnno.id, container, false))
        containerView = viewHolderImpl.itemView
        viewHolderImpl.itemView.setTag(R.id.tag_viewHolder, this)
    }

    abstract fun bindData(data: T?)

    inner class BaseViewHolderImpl(rootView: View):RecyclerView.ViewHolder(rootView), LayoutContainer {

        override val containerView: View?

        var wrapper = this@BaseViewHolder

        init {
            containerView = itemView
        }

    }
}