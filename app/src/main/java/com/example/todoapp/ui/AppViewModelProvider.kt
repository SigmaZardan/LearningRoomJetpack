package com.example.todoapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.ToDoApplication
import com.example.todoapp.ui.screens.todo.ToDoEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ToDoEntryViewModel(todoApplication().container.toDoRepository)
        }
    }
}

fun CreationExtras.todoApplication(): ToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ToDoApplication)
