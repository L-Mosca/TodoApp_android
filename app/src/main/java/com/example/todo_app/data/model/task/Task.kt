package com.example.todo_app.data.model.task

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo_app.R
import java.util.*

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "author") val creator: Int,
    @ColumnInfo(name = "responsible_first_name") val responsibleFirstName: String,
    @ColumnInfo(name = "responsible_last_name") val responsibleLastName: String,
    @ColumnInfo(name = "responsible_id") val responsibleId: Long,
    @ColumnInfo(name = "category") val category: String = "",
    @ColumnInfo(name = "short_description") val shortDescription: String,
    @ColumnInfo(name = "full_description") val fullDescription: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "priority") val priority: Int,
    @ColumnInfo(name = "create_date") val createDate: Date? = Date(),
    @ColumnInfo(name = "finish_date") val finishDate: Date? = null
) {

    companion object {

        // Task Status
        const val TASK_TO_DO = 0
        const val TASK_IN_PROGRESS = 1
        const val TASK_TO_DELIVER = 2
        const val TASK_IN_REVIEW = 3
        const val TASK_REPROVED = 4
        const val TASK_BLOCKED = 5
        const val TASK_COMPLETED = 6

        // Task Priority
        const val TASK_LOW_PRIORITY = "low"
        const val TASK_MEDIUM_PRIORITY = "medium"
        const val TASK_HIGH_PRIORITY = "high"

        // Task Category
    }

    /***
     * @param taskStatus: Integer number that represent task status
     * @param context: context to return correctly color according to the app theme
     * @return: return a ColorStateList and if taskStatus not mapped return IllegalArgumentException
     */
    fun getTaskStatusColor(taskStatus: Int, context: Context): ColorStateList? {
        return when (taskStatus) {
            TASK_TO_DO -> ContextCompat.getColorStateList(context, R.color.task_todo)
            TASK_IN_PROGRESS -> ContextCompat.getColorStateList(context, R.color.task_in_progress)
            TASK_TO_DELIVER -> ContextCompat.getColorStateList(context, R.color.task_to_deliver)
            TASK_IN_REVIEW -> ContextCompat.getColorStateList(context, R.color.task_in_review)
            TASK_REPROVED -> ContextCompat.getColorStateList(context, R.color.task_reproved)
            TASK_BLOCKED -> ContextCompat.getColorStateList(context, R.color.task_blocked)
            TASK_COMPLETED -> ContextCompat.getColorStateList(context, R.color.task_completed)
            else -> throw java.lang.IllegalArgumentException("incorrectly task status")
        }
    }

    /***
     * @param taskPriority: Integer number that represent task priority
     * @param context: context to return correctly color according to the app theme
     * @return: return a Pair object containing task priority string and ColorStateList
     * and if taskStatus not mapped return IllegalArgumentException
     */
    fun getTaskPriority(taskPriority: String, context: Context): Pair<String, ColorStateList?> {
        return when (taskPriority) {
            TASK_LOW_PRIORITY -> {
                Pair(
                    first = context.getString(R.string.task_priority_low),
                    second = ContextCompat.getColorStateList(context, R.color.task_priority_low)
                )
            }
            TASK_MEDIUM_PRIORITY -> {
                Pair(
                    first = context.getString(R.string.task_priority_medium),
                    second = ContextCompat.getColorStateList(context, R.color.task_priority_medium)
                )
            }
            TASK_HIGH_PRIORITY -> {
                Pair(
                    first = context.getString(R.string.task_priority_high),
                    second = ContextCompat.getColorStateList(context, R.color.task_priority_high)
                )
            }
            else -> throw java.lang.IllegalArgumentException("incorrectly task priority")
        }
    }

    /***
     * @param firstName: Responsible first name as String
     * @param lastName: Responsible last name as String
     * @return: Return a concatenation of first and last name
     */
    fun getResponsibleFullName(firstName: String, lastName: String) = "$firstName $lastName"

}


