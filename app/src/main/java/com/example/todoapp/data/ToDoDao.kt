package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.ToDo
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDo(todo: ToDo)

    @Update
    suspend fun updateToDo(todo: ToDo)

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Query("select * from todos")
    fun getAllToDos(): Flow<List<ToDo>>

    @Query("select * from todos where id = :id")
    fun getToDo(id: Int): Flow<ToDo>
}