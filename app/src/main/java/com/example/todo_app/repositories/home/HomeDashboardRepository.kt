package com.example.todo_app.repositories.home

import com.example.todo_app.R
import com.example.todo_app.data.model.home.CardInfoData
import com.example.todo_app.data.model.home.HomeDashboardModel
import javax.inject.Inject

class HomeDashboardRepository @Inject constructor() {

    suspend fun getList(statusFilter: String): List<HomeDashboardModel> {
        return listOf(
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_help_center,
                status = "OPEN",
                statusColor = R.color.blue_600,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_agents,
                status = "OPEN",
                statusColor = R.color.purple_400,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_home,
                status = "OPEN",
                statusColor = R.color.blue_100,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_assets,
                status = "OPEN",
                statusColor = R.color.gray_800,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_help_center,
                status = "OPEN",
                statusColor = R.color.blue_600,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_agents,
                status = "OPEN",
                statusColor = R.color.purple_400,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_home,
                status = "OPEN",
                statusColor = R.color.blue_100,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            ),
            HomeDashboardModel(
                type = "TIPO",
                typeIcon = R.drawable.ic_assets,
                status = "OPEN",
                statusColor = R.color.gray_800,
                description = "Request for a new Apple Macbook Pro",
                name = "Lucas Mosca",
                time = "2m"
            )
        )
    }

    suspend fun getCardInfoList(): List<CardInfoData> {
        return listOf(
            CardInfoData(status = "overdue", number = 421),
            CardInfoData(status = "to do", number = 81),
            CardInfoData(status = "open", number = 72),
            CardInfoData(status = "due today", number = 51)
        )
    }
}