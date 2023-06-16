package com.example.todo_app.screen.createtask.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.todo_app.R
import com.example.todo_app.data.model.task.Person

class ResponsibleArrayAdapter(
    context: Context,
    resource: Int,
    data: List<Person>
) :
    ArrayAdapter<Person>(context, resource, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view =
                LayoutInflater.from(context).inflate(R.layout.adapter_search_person, parent, false)
            viewHolder = ViewHolder()
            viewHolder.fullName = view.findViewById(R.id.tvFullName)
            viewHolder.imageName = view.findViewById(R.id.tvImageName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        getItem(position).let {
            viewHolder.apply {
                fullName.text = it?.getFullName(it.name, it.lastName)
                imageName.text = it?.getInitials(it.name, it.lastName)
            }
        }
        return view
    }
}

private class ViewHolder {
    lateinit var fullName: TextView
    lateinit var imageName: TextView
}