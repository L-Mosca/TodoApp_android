package com.example.todo_app.screen.dashboardhome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.todo_app.base.BaseAdapter
import com.example.todo_app.base.ViewHolder
import com.example.todo_app.data.model.home.HomeDashboardModel
import com.example.todo_app.data.model.task.Task
import com.example.todo_app.databinding.AdapterDashboardHomeBinding

class HomeDashboardAdapter : BaseAdapter<AdapterDashboardHomeBinding, HomeDashboardModel>() {
    override val bindingInflater: (LayoutInflater, ViewGroup) -> AdapterDashboardHomeBinding
        get() = { layoutInflater, viewGroup ->
            AdapterDashboardHomeBinding.inflate(layoutInflater, viewGroup, false)
        }

    var onItemClick: ((HomeDashboardModel) -> Unit)? = null

    override fun onBindViewHolder(
        holder: ViewHolder<AdapterDashboardHomeBinding>,
        data: HomeDashboardModel,
        position: Int
    ) {
        holder.binding.apply {
            ivRangeColor.setBackgroundColor(root.context.getColor(data.statusColor))
            ivStatus.backgroundTintList = ContextCompat.getColorStateList(root.context, data.statusColor)
            tvStatus.text = data.status
            tvTitle.text = data.description
            tvName.text = data.name
            tvTime.text = data.time

            root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }
}