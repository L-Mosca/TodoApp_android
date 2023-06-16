package com.example.todo_app.screen.dashboardhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.todo_app.base.BaseFragment
import com.example.todo_app.data.model.home.HomeDashboardModel
import com.example.todo_app.databinding.FragmentHomeDashboardBinding
import com.example.todo_app.screen.dashboardhome.adapter.HomeDashboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDashboardFragment : BaseFragment<FragmentHomeDashboardBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentHomeDashboardBinding =
        FragmentHomeDashboardBinding::inflate
    override val viewModel: HomeDashboardViewModel by activityViewModels()

    private val adapter = HomeDashboardAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    override fun initViews() {
        viewModel.getInitialData()
    }

    override fun initObservers() {
        viewModel.dashboardList.observe(viewLifecycleOwner) { dashboardList ->
            setupAdapter(dashboardList)
        }
    }

    private fun setupAdapter(data: List<HomeDashboardModel>) {
        adapter.dataList = data
        binding.rvHomeDashboard.adapter = adapter

        adapter.onItemClick = {
            Toast.makeText(requireContext(), "Teste de Clique", Toast.LENGTH_SHORT).show()
        }
    }
}