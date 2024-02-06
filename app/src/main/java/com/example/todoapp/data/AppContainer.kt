package com.example.todoapp.data

import android.content.Context


interface AppContainer {
    val toDoRepository: ToDoRepository
}


class AppDataContainer(private val context: Context) : AppContainer {
    override val toDoRepository: ToDoRepository
        get() = OfflineToDoRepository(
            todoDao = ToDoDatabase.getDatabase(context = context).toDoDao()
        )

}