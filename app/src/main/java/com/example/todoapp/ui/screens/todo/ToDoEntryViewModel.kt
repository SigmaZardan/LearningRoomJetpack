package com.example.todoapp.ui.screens.todo

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.ToDoRepository
import com.example.todoapp.model.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ToDoEntryViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {

    private val _toDoUiState = MutableStateFlow(
        ToDoUiState()
    )
    val toDoUiState = _toDoUiState.asStateFlow()

    fun updateUiState(todoDetails: ToDoDetails) {
        _toDoUiState.update { currentState ->
            currentState.copy(
                todoDetails = todoDetails, inputValidation = currentState.inputValidation.copy(
                    isTitleEmpty = false, isDescriptionEmpty = false
                )
            )
        }
    }

    suspend fun saveToDo() {
        if (validateInput()) {
            // save the data into the db
            toDoRepository.insertToDo(toDoUiState.value.todoDetails.toToDo())
            // after inserting into the db
            // successfully added toast message
            // and also make sure that
            // the title and the description are reset back
            val updatedDetails = resetTitleAndDescription()
            updateUiState(todoDetails = updatedDetails)
        }
    }

    private fun resetTitleAndDescription(uiState: ToDoDetails = _toDoUiState.value.todoDetails): ToDoDetails =
        uiState.copy(title = "", description = "")

    private fun setTitleEmpty(uiState: InputValidation = _toDoUiState.value.inputValidation): InputValidation =
        uiState.copy(isTitleEmpty = true)

    private fun setDescriptionEmpty(uiState: InputValidation = _toDoUiState.value.inputValidation): InputValidation =
        uiState.copy(isDescriptionEmpty = true)


    private fun validateInput(uiState: ToDoDetails = toDoUiState.value.todoDetails): Boolean {
        if (uiState.title.isBlank()) {
            _toDoUiState.update { currentState ->
                currentState.copy(
                    todoDetails = uiState,
                    inputValidation = setTitleEmpty()
                )
            }
        }

        if (uiState.description.isBlank()) {
            _toDoUiState.update { currentState ->
                currentState.copy(
                    todoDetails = uiState,
                    inputValidation = setDescriptionEmpty()
                )
            }
        }
        return uiState.title.isNotBlank() && uiState.description.isNotBlank()
    }
}

data class ToDoUiState(
    val todoDetails: ToDoDetails = ToDoDetails(),
    val inputValidation: InputValidation = InputValidation()
)

data class InputValidation(
    val isTitleEmpty: Boolean = false, val isDescriptionEmpty: Boolean = false
)

data class ToDoDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
)

// extension function to convert ToDoDetails into ToDo
fun ToDoDetails.toToDo(): ToDo = ToDo(
    id = id, title = title, description = description
)
// we need to convert the details into toDo to add it into the database
