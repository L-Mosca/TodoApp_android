package com.example.todo_app.screen.dashboardhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_app.base.BaseViewModel
import com.example.todo_app.data.model.home.HomeDashboardModel
import com.example.todo_app.repositories.home.HomeDashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDashboardViewModel @Inject constructor(private val repository: HomeDashboardRepository) :
    BaseViewModel() {

    val dashboardList = MutableLiveData<List<HomeDashboardModel>>()


    fun getInitialData() {
        viewModelScope.launch {
            dashboardList.postValue(repository.getList(""))
        }
    }
}