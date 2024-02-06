package com.example.todoapp.data

import com.example.todoapp.model.ToDo
import kotlinx.coroutines.flow.Flow

class OfflineToDoRepository(private val todoDao: ToDoDao) : ToDoRepository {
    override fun getAllToDosStream(): Flow<List<ToDo>> {
        return todoDao.getAllToDos()
    }

    override fun getTodoStream(id: Int): Flow<ToDo?> {
        return todoDao.getToDo(id = id)
    }

    override suspend fun insertToDo(todo: ToDo) {
        return todoDao.insertToDo(todo)
    }

    override suspend fun updateToDo(todo: ToDo) {
        return todoDao.updateToDo(todo)
    }

    override suspend fun deleteToDo(todo: ToDo) {
        return todoDao.deleteToDo(todo)
    }
}