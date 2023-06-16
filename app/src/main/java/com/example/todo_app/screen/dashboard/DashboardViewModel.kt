package com.example.todo_app.screen.dashboard

import android.content.Context
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_app.R
import com.example.todo_app.base.BaseViewModel
import com.example.todo_app.data.model.home.CardInfoData
import com.example.todo_app.repositories.home.HomeDashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val homeRepository: HomeDashboardRepository
) : BaseViewModel() {

    val switchFragment = MutableLiveData<Int>()
    val setMenuIcon = MutableLiveData<Pair<Int, Boolean>>()
    val setHomeStyle = MutableLiveData<Unit>()
    val setAssetsStyle = MutableLiveData<Unit>()
    val setHelpCenterStyle = MutableLiveData<Unit>()
    val setAgentsStyle = MutableLiveData<Unit>()

    val homeOverdueData = MutableLiveData<CardInfoData>()
    val homeTodoData = MutableLiveData<CardInfoData>()
    val homeOpenData = MutableLiveData<CardInfoData>()
    val homeDueTodayData = MutableLiveData<CardInfoData>()

    fun selectBottomNavItem(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_home -> {
                switchFragment.postValue(DashboardFragment.HOME_INDEX)
                return true
            }
            R.id.menu_assets -> {
                switchFragment.postValue(DashboardFragment.ASSETS_INDEX)
                return true
            }
            R.id.menu_help_center -> {
                switchFragment.postValue(DashboardFragment.HELP_CENTER_INDEX)
                return true
            }
            R.id.menu_agents -> {
                switchFragment.postValue(DashboardFragment.AGENTS_INDEX)
                return true
            }
            else -> return false
        }
    }

    fun setBottomMenuIcon(menuItemId: Int) {
        when (menuItemId) {
            R.id.menu_home -> setMenuIcon.postValue(Pair(DashboardFragment.HOME_INDEX, true))
            R.id.menu_assets -> setMenuIcon.postValue(Pair(DashboardFragment.ASSETS_INDEX, true))
            R.id.menu_help_center -> setMenuIcon.postValue(
                Pair(
                    DashboardFragment.HELP_CENTER_INDEX,
                    true
                )
            )
            R.id.menu_agents -> setMenuIcon.postValue(Pair(DashboardFragment.AGENTS_INDEX, true))
        }
    }

    fun setScreenDataAndStyle(pageIndex: Int) {
        when (pageIndex) {
            DashboardFragment.HOME_INDEX -> {
                setHomeStyle.postValue(Unit)
            }
            DashboardFragment.ASSETS_INDEX -> {
                setAssetsStyle.postValue(Unit)
            }
            DashboardFragment.HELP_CENTER_INDEX -> {
                setHelpCenterStyle.postValue(Unit)
            }
            DashboardFragment.AGENTS_INDEX -> {
                setAgentsStyle.postValue(Unit)
            }
        }
    }

    fun setHomeCartData(context: Context) {
        viewModelScope.launch {
            homeRepository.getCardInfoList().forEach { cardInfo ->
                when (cardInfo.status) {
                    context.getString(CardInfoData.OVERDUE_STATUS) -> homeOverdueData.postValue(cardInfo)
                    context.getString(CardInfoData.TODO_STATUS) -> homeTodoData.postValue(cardInfo)
                    context.getString(CardInfoData.OPEN_STATUS) -> homeOpenData.postValue(cardInfo)
                    context.getString(CardInfoData.DUE_TODAY_STATUS) -> homeDueTodayData.postValue(cardInfo)
                }
            }
        }
    }
}