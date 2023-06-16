package com.example.todo_app.screen.createtask

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.todo_app.R
import com.example.todo_app.base.BaseFragment
import com.example.todo_app.data.model.task.Person
import com.example.todo_app.databinding.FragmentCreateTaskBinding
import com.example.todo_app.screen.filterlist.FilterListFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CreateTaskFragment : BaseFragment<FragmentCreateTaskBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCreateTaskBinding =
        FragmentCreateTaskBinding::inflate

    override val viewModel: CreateTaskViewModel by viewModels()

    private var filterListDialog: FilterListFragment? = null
    private var personSelected: Person? = null

    private val onSendPerson = object : FilterListFragment.OnItemClickListener {
        override fun onPersonClick(person: Person) {
            personSelected = person
            setPersonFieldData(person)
        }
    }

    override fun initObservers() {
        viewModel.mostraMensagem.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initViews() {
        viewModel.showToast("Mensagem de teste")

        binding.tvFinishDate.setOnClickListener {
            setupDataPicker()
        }
        binding.tvResponsibleName.text = getString(R.string.select_responsible)
        viewModel.getPersonList()

        binding.cvResponsibleName.setOnClickListener {
            filterListDialog = FilterListFragment().apply {
                setOnPersonClicked(onSendPerson)
            }
            filterListDialog?.show(childFragmentManager, null)
        }
    }

    private fun setPersonFieldData(person: Person) {
        binding.apply {
            tvResponsibleName.text = person.getFullName(person.name, person.lastName)
            tvResponsibleName.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            cvResponsibleName.setStrokeColor(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            )
        }
    }

    private fun setupDataPicker() {
        val calendarConstraintBuilder = CalendarConstraints
            .Builder()
            .setValidator(DateValidatorPointBackward.now())

        val picker = MaterialDatePicker.Builder
            .datePicker()
            .setTitleText(R.string.select_finish_date)
            .setCalendarConstraints(calendarConstraintBuilder.build())
            .build()

        picker.addOnPositiveButtonClickListener {
            val formattedDate =
                SimpleDateFormat("MM/dd/yyyy", Locale.ROOT).format(Date(it + (1000 * 60 * 60 * 24)))
            binding.tvFinishDate.text = formattedDate
        }
        picker.show(childFragmentManager, "DATE_PICKER_TAG")
    }
}
