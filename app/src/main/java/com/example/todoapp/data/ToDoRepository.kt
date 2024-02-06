package com.example.todoapp.data

import com.example.todoapp.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    // get all the todos from the database
    // we use reactive programming for this
    // using flow
    // why flow ? it observes the data changes
    fun getAllToDosStream(): Flow<List<ToDo>>

    // this seems like if we want to observe the data from the
    // database then we can use flow as observer
    fun getTodoStream(id: Int): Flow<ToDo?>

    // insert into the db
    suspend fun insertToDo(todo: ToDo)

    // update
    suspend fun updateToDo(todo: ToDo)

    // delete
    suspend fun deleteToDo(todo: ToDo)
}
