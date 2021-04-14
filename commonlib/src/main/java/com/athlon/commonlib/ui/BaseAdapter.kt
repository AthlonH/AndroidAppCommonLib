package com.athlon.commonlib.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athlon.commonlib.R
import com.athlon.commonlib.data.IDataType

open class BaseAdapter<T>(recyclerView: RecyclerView, var createBaseViewHolder: (ViewGroup, Int) -> BaseViewHolder<T>): RecyclerView.Adapter<BaseViewHolder<T>.BaseViewHolderImpl>(), View.OnClickListener {

    var dataList: MutableList<T> = ArrayList()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    var itemClickListener: IItemClickListener<T> ?= null

    init {
        recyclerView.adapter = this
    }

    override fun onCreateViewHolder(container: ViewGroup, type: Int): BaseViewHolder<T>.BaseViewHolderImpl {
        var ktViewHolder = createBaseViewHolder(container, type)
        ktViewHolder.viewHolderImpl.itemView.setOnClickListener(this)
        return ktViewHolder.viewHolderImpl
    }

    override fun getItemCount(): Int {
        return if(dataList==null) 0 else dataList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        var data = dataList?.get(position)
        return if (data is IDataType) data.dataType else 0
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<T>.BaseViewHolderImpl, position: Int) {
        dataList?.get(position)?.let {
            viewHolder.itemView.setTag(R.id.tag_position, position)
            viewHolder.wrapper.bindData(it)

        }
    }

    override fun onClick(v: View?) {
        v?.getTag(R.id.tag_position)?.let {
            val position = it as Int
            val viewHolder = v!!.getTag(R.id.tag_viewHolder) as BaseViewHolder<T>
            itemClickListener?.onItemClick(position, dataList?.get(position))
            viewHolder.bindData(dataList?.get(position))
        }
    }


    interface IItemClickListener<T> {
        fun onItemClick(position: Int, data: T?)
    }
}


