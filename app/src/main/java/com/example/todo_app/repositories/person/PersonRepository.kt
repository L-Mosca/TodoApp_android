package com.example.todo_app.repositories.person

import com.example.todo_app.data.model.task.Person
import java.util.*
import javax.inject.Inject

class PersonRepository @Inject constructor() {

    fun getPersonList() : List<Person>{
        val list = mutableListOf<Person>()
        list.add(Person(
            id = 0,
            name = "Primeira",
            lastName = "Pessoa",
            isCreator = false,
            isResponsible = false,
            birthDate = Date(),
            email = "primeirapessoa@gmail.com",
            phoneNumber = "(15) 99999-8888"
        ))
        list.add(Person(
            id = 0,
            name = "Segunda",
            lastName = "Pessoa",
            isCreator = false,
            isResponsible = false,
            birthDate = Date(),
            email = "segundapessoa@gmail.com",
            phoneNumber = "(15) 97777-6666"
        ))
        return list
    }
}