package com.example.todo_app.screen.filterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todo_app.base.BaseAdapter
import com.example.todo_app.base.ViewHolder
import com.example.todo_app.data.model.task.Person
import com.example.todo_app.databinding.AdapterFilterListBinding

class FilterListAdapter : BaseAdapter<AdapterFilterListBinding, Any>() {
    override val bindingInflater: (LayoutInflater, ViewGroup) -> AdapterFilterListBinding
        get() = { layoutInflater, viewGroup ->
            AdapterFilterListBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
        }

    var onPersonClick: ((Person) -> Unit)? = null

    override fun onBindViewHolder(
        holder: ViewHolder<AdapterFilterListBinding>,
        data: Any,
        position: Int
    ) {
        holder.binding.apply {
            when (data) {
                is Person -> {
                    tvListItem.text = data.getFullName(data.name, data.lastName)
                }
            }

            root.setOnClickListener {
                when (data) {
                    is Person -> onPersonClick?.invoke(data)
                }
            }
        }
    }
}