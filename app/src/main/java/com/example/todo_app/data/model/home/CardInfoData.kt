package com.example.todo_app.data.model.home

import android.os.Parcelable
import com.example.todo_app.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfoData(
    val status: String,
    val number: Long = 0
) : Parcelable {

    companion object {
        val OVERDUE_STATUS = R.string.overdue_status
        val TODO_STATUS = R.string.todo_status
        val OPEN_STATUS = R.string.open_status
        val DUE_TODAY_STATUS = R.string.due_today_status
    }

}
