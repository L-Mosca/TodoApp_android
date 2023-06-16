package com.example.todo_app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo_app.data.model.task.Task

@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getTaskList(): List<Task>

    @Query("SELECT * FROM Task WHERE priority = :priority")
    fun getTaskList(priority: Int): List<Task>

    @Query("SELECT * FROM Task WHERE status = :status")
    fun getTaskList(status: String): List<Task>

    @Query("SELECT * FROM Task WHERE responsible_id = :responsibleId")
    fun getResponsibleTaskList(responsibleId: Int): List<Task>
}