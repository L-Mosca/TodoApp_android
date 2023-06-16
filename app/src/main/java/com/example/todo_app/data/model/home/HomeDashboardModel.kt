package com.example.todo_app.data.model.home

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeDashboardModel(
    val type: String,
    @DrawableRes val typeIcon: Int,
    val status: String,
    @ColorRes val statusColor: Int,
    val description: String,
    val name: String,
    val time: String
) : Parcelable
