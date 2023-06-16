package com.example.todo_app.data.model.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "is_creator") val isCreator: Boolean,
    @ColumnInfo(name = "is_responsible") val isResponsible: Boolean,
    @ColumnInfo(name = "birth_date") val birthDate: Date = Date(),
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String
) {

    /**
     * @param firstName: Person first name as String
     * @param lastName: Person last name as String
     * @return: Return person full name
     */
    fun getFullName(firstName: String, lastName: String) = "$firstName $lastName"

    /**
     * @param firstName: Person first name as String
     * @param lastName: person last name as String
     * @return: Return person initials or IllegalArgumentException if args are incorrectly
     */
    fun getInitials(firstName: String?, lastName: String?) : String {
        val firstNameInitial = (firstName?.get(0) ?: "").toString()
        val lastNameInitial = (lastName?.get(0) ?: "").toString()
        return when {
            firstNameInitial.isNotEmpty() && lastNameInitial.isNotEmpty() -> "$firstNameInitial $lastNameInitial"
            firstNameInitial.isNotEmpty() && lastNameInitial.isEmpty() -> "${firstName?.substring(0,1)}"
            firstNameInitial.isEmpty() && lastNameInitial.isNotEmpty() -> "${lastName?.substring(0,1)}"
            else -> throw java.lang.IllegalArgumentException("first and last name are incorrectly")
        }
    }
}