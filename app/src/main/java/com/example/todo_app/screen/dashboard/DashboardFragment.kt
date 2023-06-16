package com.example.todo_app.screen.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.todo_app.App
import com.example.todo_app.R
import com.example.todo_app.data.model.task.Task
import com.example.todo_app.databinding.FragmentDashboardBinding
import com.example.todo_app.screen.dashboard.adapter.ViewPagerAdapter
import com.example.todo_app.screen.dashboardagents.AgentsDashboardFragment
import com.example.todo_app.screen.dashboardassets.AssetsDashboardFragment
import com.example.todo_app.screen.dashboardhelpcenter.HelpCenterDashboardFragment
import com.example.todo_app.screen.dashboardhome.HomeDashboardFragment
import com.example.todo_app.util.TransitionAnimation
import com.example.todo_app.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    companion object {
        const val HOME_INDEX = 0
        const val ASSETS_INDEX = 1
        const val HELP_CENTER_INDEX = 2
        const val AGENTS_INDEX = 3
    }

    enum class Type {
        HOME,
        ASSETS,
        HELP_CENTER,
        AGENTS
    }

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private var actualScreen = Type.HOME

    val viewModel: DashboardViewModel by viewModels()
    lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ViewPagerAdapter(this)
        initViews()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        setViewPager()
        setBottomNavigationListener()

        Thread {
            val app = requireActivity().application as App
            val dao = app.db.taskDao()
            val response = dao.getTaskList(Task.TASK_LOW_PRIORITY)
            requireActivity().runOnUiThread {
                Log.e("testList", response.toString())
            }
        }.start()

        binding.ivAdd.setOnClickListener {
            val directions =
                DashboardFragmentDirections.actionDashboardFragmentToCreateTaskFragment()
            navigate(directions, animation = TransitionAnimation.TRANSLATE_FROM_DOWN)
        }
    }

    private fun initObservers() {
        viewModel.homeOverdueData.observe(viewLifecycleOwner) { cardInfo ->
            binding.apply {
                includeDashboardCard.tvOverdueTitle.text = cardInfo.status
                includeDashboardCard.tvOverdueNumber.text = cardInfo.number.toString()
            }
        }

        viewModel.homeTodoData.observe(viewLifecycleOwner) { cardInfo ->
            binding.apply {
                includeDashboardCard.tvToDoTitle.text = cardInfo.status
                includeDashboardCard.tvToDoNumber.text = cardInfo.number.toString()
            }
        }

        viewModel.homeOpenData.observe(viewLifecycleOwner) { cardInfo ->
            binding.apply {
                includeDashboardCard.tvOpenTitle.text = cardInfo.status
                includeDashboardCard.tvOpenNumber.text = cardInfo.number.toString()
            }
        }

        viewModel.homeDueTodayData.observe(viewLifecycleOwner) { cardInfo ->
            binding.apply {
                includeDashboardCard.tvDueTodayTitle.text = cardInfo.status
                includeDashboardCard.tvDueTodayNumber.text = cardInfo.number.toString()
            }
        }

        viewModel.setHomeStyle.observe(viewLifecycleOwner) {
            actualScreen = Type.HOME
            setScreenDataAndStyle(
                screenTitle = "Inventory Management",
                topImageBackground = R.drawable.dashboard_home_gradient
            )
            viewModel.setHomeCartData(requireContext())
        }

        viewModel.setAssetsStyle.observe(viewLifecycleOwner) {
            actualScreen = Type.ASSETS
            setScreenDataAndStyle(
                screenTitle = "Assets",
                topImageBackground = R.drawable.dashboard_assets_gradient
            )
        }

        viewModel.setHelpCenterStyle.observe(viewLifecycleOwner) {
            actualScreen = Type.HELP_CENTER
            setScreenDataAndStyle(
                screenTitle = "Help Center",
                topImageBackground = R.drawable.dashboard_home_gradient
            )
        }

        viewModel.setAgentsStyle.observe(viewLifecycleOwner) {
            actualScreen = Type.AGENTS
            setScreenDataAndStyle(
                screenTitle = "Agents",
                topImageBackground = R.drawable.dashboard_home_gradient
            )
        }

        viewModel.setMenuIcon.observe(viewLifecycleOwner) {
            binding.bnvDashboard.menu.getItem(it.first).isChecked = it.second
        }

        viewModel.switchFragment.observe(viewLifecycleOwner) { fragmentIndex ->
            binding.vpDashboardScreen.setCurrentItem(fragmentIndex, true)
        }
    }

    private fun setViewPager() {
        adapter.addFragment(HomeDashboardFragment())
        adapter.addFragment(AssetsDashboardFragment())
        adapter.addFragment(HelpCenterDashboardFragment())
        adapter.addFragment(AgentsDashboardFragment())

        binding.vpDashboardScreen.adapter = adapter

        // set animation to navigate between view pager fragments
        //viewPager.setPageTransformer(YourPageTransformer())
    }

    private fun setBottomNavigationListener() {
        binding.bnvDashboard.setOnItemSelectedListener { menuItem ->
            viewModel.selectBottomNavItem(menuItem)
        }

        binding.vpDashboardScreen.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Refresh bottomNavigationView menu icon state when viewPager switch fragment with scroll
                viewModel.setBottomMenuIcon(binding.bnvDashboard.menu.getItem(position).itemId)
                viewModel.setScreenDataAndStyle(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun setScreenDataAndStyle(screenTitle: String, topImageBackground: Int) {
        binding.tvTitle.text = screenTitle
        binding.ivBackground.background =
            ContextCompat.getDrawable(requireContext(), topImageBackground)
    }
}