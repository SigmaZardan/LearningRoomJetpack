package com.example.todoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.ToDoNavGraph

@Composable
fun ToDoApp(navController: NavHostController = rememberNavController()) {
    ToDoNavGraph(navController = navController)
}