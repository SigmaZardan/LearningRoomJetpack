package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todos")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val title: String
)
