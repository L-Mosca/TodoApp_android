package com.example.todo_app.screen.filterlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.todo_app.App
import com.example.todo_app.R
import com.example.todo_app.base.BaseDialog
import com.example.todo_app.data.model.task.Person
import com.example.todo_app.data.model.task.Task
import com.example.todo_app.databinding.FragmentFilterListBinding
import com.example.todo_app.screen.filterlist.adapter.FilterListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class FilterListFragment : BaseDialog() {

    private var _binding: FragmentFilterListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilterListViewModel by viewModels()

    interface OnItemClickListener {
        fun onPersonClick(person: Person)
    }

    private var listener: OnItemClickListener? = null


    companion object {
        fun newInstance(): FilterListFragment {
            return FilterListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        viewModel.fetchList(FilterListViewModel.ARGUMENT_PERSON_LIST)

        Thread {
            val app = requireActivity().application as App
            val dao = app.db.taskDao()

            val getTask = dao.getTaskList()
            val getTaskLowPriority = dao.getTaskList(Task.TASK_LOW_PRIORITY)


            requireActivity().runOnUiThread {
                Toast.makeText(requireContext(), "$getTask\n$getTaskLowPriority", Toast.LENGTH_SHORT).show()
            }


        }.start()
    }

    private fun initObservers() {
        viewModel.personList.observe(viewLifecycleOwner) {
            val adapter = FilterListAdapter()
            adapter.dataList = it
            binding.tvFilterTitle.text = getString(R.string.person_list_title)
            binding.rvList.adapter = adapter

            adapter.onPersonClick = { person ->
                listener?.onPersonClick(person)
                dismiss()
            }
        }
    }

    fun setOnPersonClicked(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }
}
