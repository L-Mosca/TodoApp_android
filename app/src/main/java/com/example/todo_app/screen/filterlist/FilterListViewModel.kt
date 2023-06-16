package com.example.todo_app.screen.filterlist

import androidx.lifecycle.MutableLiveData
import com.example.todo_app.base.BaseViewModel
import com.example.todo_app.data.model.task.Person
import com.example.todo_app.repositories.person.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterListViewModel @Inject constructor(private val personRepository: PersonRepository) : BaseViewModel() {

    val personList = MutableLiveData<List<Person>>()

    companion object {
        const val ARGUMENT_PERSON_LIST = "person"
    }

    fun fetchList(argument: String) {
        when (argument) {
            ARGUMENT_PERSON_LIST -> personList.postValue(personRepository.getPersonList())
        }
    }
}